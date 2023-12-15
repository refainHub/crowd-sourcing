package cn.sfcoder.service;

import cn.sfcoder.dto.TaskDTO;
import cn.sfcoder.vo.ResponseVO;
import cn.sfcoder.vo.TaskVO;

import java.util.List;

/**
 * @Author: refain
 * @Description: 服务类
 * @Date: 2023/12/15 21:01
 * @Version: 1.0
 */
public interface TaskService {

    ResponseVO updateTaskInfo(TaskDTO toDTO);

    ResponseVO findTaskByUserId(Integer userId);

    List<TaskVO> findTaskByTaskIdList(List<Integer> taskIdList);

    ResponseVO findAllTasks();

    ResponseVO issueTask(TaskDTO toDTO);

    ResponseVO selectTaskByLabel(Integer tag, Integer if_finished, String name);

    ResponseVO findTaskByTaskId(Integer userId, Integer taskId);

    TaskVO findTaskByTaskId(Integer taskId);

    ResponseVO recommendTaskByLabel(Integer tag, Integer if_finished, Integer userId);

    ResponseVO recommendAllTasks(Integer userId);

//    ResponseVO ifShown(Integer userId, Integer taskId);


}