package cn.sfcoder.controller;


import cn.sfcoder.annotation.CheckStatus;
import cn.sfcoder.mapper.TaskMapperWrapper;
import cn.sfcoder.po.Comment;
import cn.sfcoder.po.Task;
import cn.sfcoder.po.enums.UserIdentity;
import cn.sfcoder.service.ReportService;
import cn.sfcoder.service.impl.TaskScheduleManager;
import cn.sfcoder.util.reportStrategy.BadStrategy;
import cn.sfcoder.util.reportStrategy.SimilarStrategy;
import cn.sfcoder.vo.CommentVO;
import cn.sfcoder.vo.ReportVO;

import cn.sfcoder.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 22:25
 * @Version: 1.0
 */

@RestController
@RequestMapping("/collect/report")
public class ReportController {


    @Autowired
    ReportService reportService;

    @Autowired
    SimilarStrategy similarStrategy;

    @Autowired
    BadStrategy badStrategy;

    @CheckStatus(identity = UserIdentity.DELIVER)
    @GetMapping("/lookReports")
    public ResponseVO lookReports(@RequestParam Integer taskId) {
        return reportService.lookReports(taskId);
    }


    /**
     * 根据userId和taskId获得report信息
     *
     * @param userId
     * @param taskId
     * @return
     */
    @GetMapping("/findReport")
    @Deprecated
    public ResponseVO findReport(@RequestParam Integer userId, @RequestParam Integer taskId) {
        return reportService.findReport(userId, taskId);
    }

    //提交任务
    @PostMapping("/commit")
    public ResponseVO commitReport(ReportVO reportVO) {
        return reportService.commitReport(reportVO.toDTO());
    }


    @PostMapping("/updateReport")
    @Deprecated
    public ResponseVO updateReport(ReportVO reportVO) {
        return reportService.updateReport(reportVO.toDTO());
    }


    /**
     * 系统依据众包工人所提交的众测报告，检测已有报告与其的相似度，并将相似报告展示给众包工人
     *
     * @param reportId
     * @return
     */
    @CheckStatus(identity = UserIdentity.ALL)
    @GetMapping("/similarReports")
    ResponseVO findSimilarReportsFromSameTask(@RequestParam int reportId) {
        return reportService.findTargetReports(similarStrategy,reportId);
    }

    @GetMapping("/lowQuality")
    ResponseVO findLowQualityReportsFromSameTask(@RequestParam int reportId) {
        return reportService.findTargetReports(badStrategy,reportId);
    }

    /**
     * 展示一个任务下所有报告的相似关系
     * @param taskId
     * @return
     */
    @CheckStatus(identity = UserIdentity.ALL)
    @GetMapping("/reportSimilarity")
    @Deprecated
    ResponseVO reportsSimailarity(@RequestParam int taskId) {
        return reportService.reportsSimilarity(taskId);
    }



    /**
     * 众包工人已有报告进行量化评分、输入文本进行报告评价
     *
     * @param commentVO
     * @return
     */
    @CheckStatus(identity = UserIdentity.ALL)
    @PostMapping("/markAndComment")
    ResponseVO giveMarkAndComment(@RequestBody CommentVO commentVO) {
        return reportService.giveMarkAndComment(commentVO.toDTO());
    }


    /**
     * 获得报告下面的所有评论
     *
     * @param reportId
     * @return
     */
    @CheckStatus(identity = UserIdentity.ALL)
    @GetMapping("/getCommentsUnderReport")
    ResponseVO getCommentsUnderReport(@RequestParam int reportId) {
        return reportService.getCommentsUnderReport(reportId);
    }

    @CheckStatus(identity = UserIdentity.ALL)
    @PostMapping("/addAnnotation")
    ResponseVO giveAnnotation(@RequestBody ReportVO reportVO) {
        return reportService.giveAnnotation(reportVO.toDTO(),reportVO.getAnnotationUserId());
    }

    @CheckStatus(identity = UserIdentity.ALL)
    @GetMapping("/showAnnotation")
    ResponseVO showAnnotation(@RequestParam int taskId, @RequestParam int userId) {
        return reportService.showAnnotation(taskId, userId);
    }

    @CheckStatus(identity = UserIdentity.ALL)
    @GetMapping("/findCoworkers")
    ResponseVO findCoworkers(@RequestParam int taskId, @RequestParam int userId) {
        return reportService.findCoworkers(taskId, userId);
    }

    @CheckStatus(identity = UserIdentity.DELIVER)
    @GetMapping("/cluster")
    ResponseVO clusterReports(@RequestParam int taskId){
        return reportService.findClusterResult(taskId);
    }


    @Autowired
    TaskScheduleManager manager;

    @Autowired
    TaskMapperWrapper taskMapperWrapper;

    @GetMapping("/end/{taskId}")
    void end(@PathVariable int taskId){
        Task task=taskMapperWrapper.selectById(taskId);
        task.setDate(System.currentTimeMillis()+1000);
        manager.update(task);
    }
}

