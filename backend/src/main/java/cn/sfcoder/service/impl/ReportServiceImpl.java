package cn.sfcoder.service.impl;

import cn.sfcoder.dto.CommentDTO;
import cn.sfcoder.dto.ReportDTO;
import cn.sfcoder.exception.MyException;
import cn.sfcoder.mapper.*;
import cn.sfcoder.po.*;
import cn.sfcoder.po.enums.WorkStatus;
import cn.sfcoder.service.ReportService;
import cn.sfcoder.util.AnnotationUtil;
import cn.sfcoder.util.FileUtil;
import cn.sfcoder.util.reportStrategy.ReportStrategy;
import cn.sfcoder.vo.*;
import cn.sfcoder.vo.http.CommentHttpStatus;
import cn.sfcoder.vo.http.TaskHttpStatus;
import cn.sfcoder.vo.http.WorkHttpsStatus;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 22:08
 * @Version: 1.0
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    TaskMapperWrapper taskMapper;

    @Autowired
    ReportMapper reportMapper;

    @Autowired
    ReportImageMapper reportImageMapper;

    @Autowired
    WorkMapper workMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CoworkersMapper coworkersMapper;

    @Autowired
    ReportClusterMapper reportClusterMapper;

    /**
     * 发包方
     * 根据任务id查找任务下的全部报告
     *
     * @param taskId，任务id
     * @return 任务下所有工人提交报告的最后版本列表
     */
    @Override
    public ResponseVO lookReports(Integer taskId) {
        Map<String, Object> map = new HashMap<>();
        map.put("taskId", taskId);
        // 查询参与任务的工人列表
        List<Work> workers = workMapper.selectByMap(map);
        // 没有人参与，直接返回
        if (workers.size() == 0) {
            return ResponseVO.succeed();
        }
        List<VO> t = new ArrayList<>();
        for (Work worker : workers) {
            // 查找工人最新一次报告
            Report report = reportMapper.selectLastByUserIdAndTaskId(worker.getUserId(), taskId);
            // 不存在则查看下一位工人报告
            if (report == null) {
                continue;
            }
            ReportDTO reportDTO = report.toDTO();
            // 为每个report插入工作完成状态
            reportDTO.setWorkStatus(worker.getFinish());
            // 为每个report插入报告发布者的email
            reportDTO.setName(userMapper.selectById(worker.getUserId()).getName());
            reportDTO.setEmail(userMapper.selectById(worker.getUserId()).getEmail());
            // 为每个report插入图片url
            reportDTO.setPaths(findReportImagesById(reportDTO.getId()));
            t.add(reportDTO.toVO());
        }
        return ResponseVO.succeed(t);
    }

    /**
     * 提交报告
     *
     * @param reportDTO
     * @return
     */
    @Override
    public ResponseVO commitReport(ReportDTO reportDTO) {
        reportDTO.setNote(AnnotationUtil.convert(reportDTO.getNote()));
        reportDTO.setSteps(AnnotationUtil.convert(reportDTO.getSteps()));
        Report report=reportDTO.toPO();
        reportMapper.insert(report);

        if (reportDTO.getImages() != null) {
            for (MultipartFile file : reportDTO.getImages()) {
                if (FileUtil.checkFileType(file, "png", "jpg", "jpeg", "gif", "bmp", "psd", "webp", "svg")) {
                    ReportImage reportImage = new ReportImage();
                    reportImage.setReportId(report.getId());
                    reportImage.setImage(FileUtil.save(file, FileUtil.REPORT_IMAGE, reportDTO.getTaskId(), reportDTO.getUserId()));
                    reportImageMapper.insert(reportImage);
                }
            }
        }
        if (reportDTO.getToKeep() != null) {
            for (String s : reportDTO.getToKeep()) {
                ReportImage reportImage = new ReportImage();
                reportImage.setReportId(report.getId());
                reportImage.setImage(s);
                reportImageMapper.insert(reportImage);
            }
        }

        return ResponseVO.succeed(report.toDTO().toVO());
    }

    /**
     * 更新报告
     *
     * @param reportDTO
     * @return
     */
    @Override
    public ResponseVO updateReport(ReportDTO reportDTO) {
        return null;

    }

    private List<String> findReportImagesById(Integer id) {
        Map<String, Object> select = new HashMap<>();
        select.put("reportId", id);
        List<ReportImage> list = reportImageMapper.selectByMap(select);
        List<String> ans = new ArrayList<>();
        for (ReportImage reportImage : list) {
            ans.add(reportImage.getImage());
        }
        return ans;
    }

    @Override
    public ResponseVO findTargetReports(ReportStrategy reportStrategy, int reportId) {
        int taskId = reportMapper.selectById(reportId).getTaskId();
        Map<String, Object> map = new HashMap<>();
        map.put("taskId", taskId);
        List<Report> reports = reportMapper.selectByMap(map);
        reports.sort((a,b)->b.getId()-a.getId());
        if (reports.size() == 0) {
            return ResponseVO.succeed();
        }
        Set<Integer> set=new HashSet<>();
        List<ReportDTO> reportDTOs = new ArrayList<>();
        int curReportUserId=reportMapper.selectById(reportId).getUserId();
        for (Report report : reports) {
            if (!Objects.equals(report.getId(), reportId)
                    && !Objects.equals(report.getUserId(), curReportUserId)
                    && !set.contains(report.getUserId())) {
                ReportDTO reportDTO = report.toDTO();
                User user = userMapper.selectById(report.getUserId());
                // 为每个report插入报告发布者的name
                reportDTO.setName(user.getName());
                // 为每个report插入报告发布者的email
                reportDTO.setEmail(user.getEmail());
                // 为每个report插入图片url
                reportDTO.setPaths(findReportImagesById(reportDTO.getId()));
                reportDTOs.add(reportDTO);
            }
            set.add(report.getUserId());
        }
        return ResponseVO.succeed(reportStrategy.choose(reportId,reportDTOs));
    }

    /**
     * 众包工人已有报告进行量化评分、输入文本进行报告评价
     *
     * @param commentDTO
     * @return
     */
    @Override
    public ResponseVO giveMarkAndComment(CommentDTO commentDTO) {
        List<Comment> commentCheck = commentMapper.selectComment(commentDTO.getReportId(), commentDTO.getUserId());
        // 之前没有打过分/评价过
        if (commentCheck.size() == 0) {
            // 在comment表插入评分和评价
            Comment comm = commentDTO.toPO();
            comm.setId(null);
            commentMapper.insert(comm);

            // 更新report表中的star和starNum
            Report report = reportMapper.selectById(comm.getReportId());
            // 原先的评分和评分数量
            double preStar = report.getStar();
            int preStarNum = report.getStarNum();

            UpdateWrapper<Report> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", report.getId());
            Report r1 = new Report();
            r1.setStarNum(preStarNum + 1);
            r1.setStar((preStar * preStarNum + comm.getMark()) * 1.0 / (preStarNum + 1));
            reportMapper.update(r1, updateWrapper);

            // 返回报告新的评分和总评分数
            Report res = reportMapper.selectById(report.getId());
            return ResponseVO.succeed(res.toDTO().toVO());
        } else {
            return ResponseVO.fail(CommentHttpStatus.COMMENT_ALREADY_EXISTS);
        }
    }

    /**
     * 获得报告下面的所有评论
     *
     * @param reportId
     * @return
     */
    @Override
    public ResponseVO getCommentsUnderReport(int reportId) {
        List<Comment> comments = commentMapper.selectCommentByReportId(reportId);
        if (comments.size() == 0) {
            return ResponseVO.succeed();
        } else {
            List<VO> commentVOs = new ArrayList<>();
            for (int i = 0; i < comments.size(); i++) {
                commentVOs.add(comments.get(i).toDTO().toVO());
            }
            return ResponseVO.succeed(commentVOs);
        }
    }

    @Override
    public ResponseVO giveAnnotation(ReportDTO reportDTO, Integer annotationUserId) {
        reportMapper.updateById(reportDTO.toPO());
        Map<String, Object> map = new HashMap<>();
        map.put("userId", reportDTO.getUserId());
        map.put("taskId", reportDTO.getTaskId());
        List<Work> works = workMapper.selectByMap(map);
        if (works.size() != 1) {
            return ResponseVO.fail(WorkHttpsStatus.WORK_NEVER_PART);
        }
        Integer id = works.get(0).getId();
        Map<String, Object> map2 = new HashMap<>();
        map2.put("workId", id);
        map2.put("coworkerId", annotationUserId);
        List<Coworkers> coworkers = coworkersMapper.selectByMap(map2);
        if (coworkers.size() == 0) {
            coworkersMapper.insert(new Coworkers(null, id, annotationUserId));
        }
        return ResponseVO.succeed();
    }

    @Override
    public ResponseVO showAnnotation(int taskId, int userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("taskId", taskId);
        map.put("userId", userId);
        List<Report> reports = reportMapper.selectByMap(map);
        if (reports.size() == 0) {
            return ResponseVO.succeed();
        }
        reports.sort((a, b) -> b.getId() - a.getId());
        User user=userMapper.selectById(userId);
        List<VO> ans = new ArrayList<>();
        for (Report report : reports) {
            ReportDTO reportDTO=report.toDTO();
            reportDTO.setPaths(findReportImagesById(reportDTO.getId()));
            reportDTO.setName(user.getName());
            reportDTO.setEmail(user.getEmail());
            ans.add(reportDTO.toVO());
        }
        return ResponseVO.succeed(ans);
    }

    @Override
    public ResponseVO findCoworkers(int taskId, int userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("taskId", taskId);
        map.put("userId", userId);
        List<Work> works = workMapper.selectByMap(map);
        if (works.size() != 1) {
            return ResponseVO.fail(WorkHttpsStatus.WORK_NEVER_PART);
        }
        Integer id = works.get(0).getId();
        Map<String, Object> map2 = new HashMap<>();
        map2.put("workId", id);
        List<Coworkers> coworkers = coworkersMapper.selectByMap(map2);
        List<UserVO> coworkersIds = new ArrayList<>();
        for (Coworkers coworker : coworkers) {
            coworkersIds.add((UserVO) userMapper.selectById(coworker.getCoworkerId()).toDTO().toVO());
        }
        return ResponseVO.succeed(new CoworkersVO(coworkersIds));
    }

    /**
     * 查找众包工人持有的report
     *
     * @param userId
     * @param taskId
     * @return
     */
    @Override
    @Deprecated
    public ResponseVO findReport(Integer userId, Integer taskId) {
        try {
            ReportDTO reportDTO = verifyWorkStatus(userId, taskId);
            if (reportDTO.getId() != null) {
                reportDTO.setPaths(findReportImagesById(reportDTO.getId()));
                return ResponseVO.succeed(reportDTO.toVO());
            } else {
                return ResponseVO.succeed();
            }
        } catch (MyException myException) {
            return ResponseVO.fail(myException.getHttpStatus());
        }
    }

    @Deprecated
    private ReportDTO verifyWorkStatus(int userId, int taskId) throws MyException {
        Map<String, Object> select = new HashMap<>();
        select.put("userId", userId);
        select.put("taskId", taskId);
        // 在数据库中查找匹配的work，若不存在该work则不允许提交report
        List<Work> works = workMapper.selectByMap(select);
        if (works == null || works.size() == 0) {
            throw MyException.of(TaskHttpStatus.TASK_NOT_EXIST);
        }
        Work work = works.get(0);
        // 工作失败说明没有报告
        if (work.getFinish().equals(WorkStatus.FAIL)) {
            ReportDTO res = new ReportDTO();
            res.setWorkStatus(WorkStatus.FAIL);
            return res;
        }
        List<Report> reports = reportMapper.selectByMap(select);
        // 如果该任务没完成
        // 查询数据库，看该任务有没有过期,若过期且用户没提交报告，则设置work失败，否则设置work完成
        // 若完成，则不允许提交report
        if (work.getFinish().equals(WorkStatus.TO_FINISH)) {
            Task task = taskMapper.selectById(taskId);
            if ((reports == null || reports.size() == 0)) {
                if (task.getDate() <= System.currentTimeMillis()) {
                    work.setFinish(WorkStatus.FAIL);
                    workMapper.updateById(work);
                }
                ReportDTO res = new ReportDTO();
                res.setWorkStatus(work.getFinish());
                return res;
            } else if (task.getDate() <= System.currentTimeMillis()) {
                work.setFinish(WorkStatus.FINISH);
                workMapper.updateById(work);
            }
        }
        ReportDTO res = reports.get(0).toDTO();
        res.setWorkStatus(work.getFinish());
        return res;
    }


    /**
     * 展示任务下所有报告的相似关系
     *
     * @param taskId
     * @return
     */
    @Override
    @Deprecated
    public ResponseVO reportsSimilarity(int taskId) {

        return null;
    }

    @Override
    public ResponseVO findClusterResult(int taskId) {
        Map<String,Object> map=new HashMap<>();
        map.put("taskId",taskId);
        List<ReportCluster> reportClusters = reportClusterMapper.selectByMap(map);
        if (reportClusters.size()==0){
            return ResponseVO.fail(TaskHttpStatus.TASK_HAS_NOT_FINISHED);
        }else {
            return ResponseVO.succeed(convert(reportClusters.get(0).getClusterSet()));
        }
    }

    /**
     * 转换
     * @param cluster_set
     * @return
     */
    private ClusterVO convert(String cluster_set) {
        cluster_set=cluster_set.substring(1,cluster_set.length()-1);
        String[] strings=cluster_set.split("##");
        List<List<Integer>> ans1=new ArrayList<>();
        List<String> ans2=new ArrayList<>();
        for (String string : strings) {
            if (string.length()>0){
                List<Integer> list=new ArrayList<>();
                String[] r=string.split(" ");
                for (String s : r) {
                    if (!s.startsWith("^")){
                        list.add(Integer.parseInt(s));
                    }else{
                        ans2.add(s.substring(19));
                    }
                }
                ans1.add(list);
            }
        }
        ClusterVO clusterVO=new ClusterVO();
        clusterVO.setResult(ans1);
        clusterVO.setPath(ans2);
        return clusterVO;
    }


}