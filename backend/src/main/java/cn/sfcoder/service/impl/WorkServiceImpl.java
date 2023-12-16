package cn.sfcoder.service.impl;

import cn.sfcoder.dto.WorkDTO;
import cn.sfcoder.mapper.ReportMapper;
import cn.sfcoder.mapper.TaskMapperWrapper;
import cn.sfcoder.mapper.WorkMapper;
import cn.sfcoder.po.Task;
import cn.sfcoder.po.Work;
import cn.sfcoder.po.enums.WorkStatus;
import cn.sfcoder.service.ReportService;
import cn.sfcoder.service.WorkService;
import cn.sfcoder.vo.ResponseVO;
import cn.sfcoder.vo.VO;
import cn.sfcoder.vo.http.WorkHttpsStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 22:17
 * @Version: 1.0
 */
@Service
public class WorkServiceImpl implements WorkService {


    @Autowired
    WorkMapper workMapper;

    @Autowired
    TaskMapperWrapper taskMapper;

    @Autowired
    ReportMapper reportMapper;

    @Autowired
    ReportService reportService;


    /**
     * 众包工人根据工人id和选择的任务状态获得他领取的任务id
     *
     * @param userId，工人id
     * @param workStatus，工作状态
     * @return 符合工作状态且众包工人所持有的任务列表
     */
    @Override
    public ResponseVO findTaskByUserId(int userId, WorkStatus workStatus) {
        Map<String, Object> selectByUserIdAndWorkStatus = new HashMap<>();
        selectByUserIdAndWorkStatus.put("userId", userId);
        selectByUserIdAndWorkStatus.put("finish", workStatus);
        // 根据用户id找到所有从事的工作
        List<Work> workList = workMapper.selectByMap(selectByUserIdAndWorkStatus);
        List<VO> taskVOS = new ArrayList<>();
        // 如果没有工作，则返回空
        for (Work work : workList) {
            taskVOS.add(taskMapper.selectById(work.getTaskId()).toDTO().toVO());
        }
        return ResponseVO.succeed(taskVOS);
    }


    /**
     * 众包工人参与任务
     *
     * @param workDTO
     * @return
     */
    @Override
    public ResponseVO partTask(WorkDTO workDTO) {
        // 查询数据库，看该任务有没有过期或满员
        Task task = taskMapper.selectById(workDTO.getTaskId());
        if (task.getDate() <= System.currentTimeMillis() || task.getRemain() <= 0) {
            return ResponseVO.fail(WorkHttpsStatus.TASK_OVERTIME);
        }
        // 查询数据库，看该用户是否参加过这个任务
        Map<String, Object> part = new HashMap<>();
        part.put("userId", workDTO.getUserId());
        part.put("taskId", workDTO.getTaskId());
        List<Work> works = workMapper.selectByMap(part);
        if (works.size() > 0) {
            return ResponseVO.fail(WorkHttpsStatus.WORK_DUPLICATE_PART);
        }
        //用户参加任务
        workMapper.insert(workDTO.toPO());
        //任务剩余人数减少
        task.setRemain(task.getRemain() - 1);
        taskMapper.updateById(task);
        return ResponseVO.succeed(workDTO.toVO());
    }


    @Override
    public ResponseVO finishTask(WorkDTO workDTO) {
        // 查询工作状态
        WorkStatus workStatus = findWorkStatus(workDTO.getUserId(), workDTO.getTaskId());
        // 没参加任务，报错
        if (workStatus == null) {
            return ResponseVO.fail(WorkHttpsStatus.WORK_NEVER_PART);
        }
        // 如果工作已经完成或失败，则直接返回
        if (!workStatus.equals(WorkStatus.TO_FINISH)) {
            return ResponseVO.fail(WorkHttpsStatus.TASK_OVERTIME);
        }
        workMapper.updateById(workDTO.toPO());
        return ResponseVO.succeed();
    }

    @Override
    public WorkStatus findWorkStatus(int userId, int taskId) {
        Map<String, Object> select = new HashMap<>();
        select.put("userId", userId);
        select.put("taskId", taskId);
        List<Work> works = workMapper.selectByMap(select);
        if (works == null || works.size() == 0)
            return null;
        return works.get(0).getFinish();
    }
}