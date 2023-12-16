package cn.sfcoder.service;

import cn.sfcoder.dto.WorkDTO;
import cn.sfcoder.po.enums.WorkStatus;
import cn.sfcoder.vo.ResponseVO;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 22:16
 * @Version: 1.0
 */
public interface WorkService {

    ResponseVO findTaskByUserId(int userId, WorkStatus workStatus);

    ResponseVO partTask(WorkDTO workDTO);

    ResponseVO finishTask(WorkDTO workDTO);

    WorkStatus findWorkStatus(int userId, int taskId);

}