package cn.sfcoder.service;

import cn.sfcoder.dto.CommentDTO;
import cn.sfcoder.dto.ReportDTO;
import cn.sfcoder.util.reportStrategy.ReportStrategy;
import cn.sfcoder.vo.ResponseVO;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 21:51
 * @Version: 1.0
 */
public interface ReportService {

    ResponseVO lookReports(Integer taskId);

    ResponseVO commitReport(ReportDTO reportDTO);

    @Deprecated
    ResponseVO updateReport(ReportDTO reportDTO);

    ResponseVO findReport(Integer userId, Integer taskId);

    ResponseVO findTargetReports(ReportStrategy reportStrategy, int reportId);

    ResponseVO giveMarkAndComment(CommentDTO commentDTO);

    ResponseVO getCommentsUnderReport(int reportId);

    ResponseVO giveAnnotation(ReportDTO reportDTO,Integer annotationUserId);

    ResponseVO showAnnotation(int taskId, int userId);

    ResponseVO findCoworkers(int taskId, int userId);

    ResponseVO reportsSimilarity(int taskId);

    ResponseVO findClusterResult(int taskId);
}