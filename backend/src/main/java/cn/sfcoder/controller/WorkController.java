package cn.sfcoder.controller;

import cn.sfcoder.annotation.CheckStatus;
import cn.sfcoder.po.enums.UserIdentity;
import cn.sfcoder.po.enums.WorkStatus;
import cn.sfcoder.service.WorkService;
import cn.sfcoder.vo.ResponseVO;
import cn.sfcoder.vo.WorkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 22:30
 * @Version: 1.0
 */
@RestController
@RequestMapping("/collect/work")
public class WorkController {

    @Autowired
    WorkService workService;


    /**
     * 众包工人参与任务
     *
     * @param workVO
     * @param
     * @return
     */
    @CheckStatus(identity = UserIdentity.WORKER)
    @PostMapping("/part")
    ResponseVO partTask(@RequestBody WorkVO workVO) {
        return workService.partTask(workVO.toDTO());
    }

    /**
     * 众包工人完成任务
     *
     * @param workVO
     * @return
     */
    @CheckStatus(identity = UserIdentity.WORKER)
    @PostMapping("/finish")
    ResponseVO finishTask(@RequestBody WorkVO workVO) {
        workVO.setFinish(WorkStatus.FINISH);
        return workService.finishTask(workVO.toDTO());
    }
}
