package cn.sfcoder.service.impl;

import cn.sfcoder.mapper.TaskMapperWrapper;
import cn.sfcoder.service.RuleService;
import cn.sfcoder.vo.ResponseVO;
import cn.sfcoder.vo.RuleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/14 17:34
 * @Version: 1.0
 */
@Service
public class RuleServiceImpl implements RuleService {



    @Autowired
    TaskMapperWrapper taskMapperWrapper;

    @Override
    public ResponseVO getRules() {

        return ResponseVO.succeed(taskMapperWrapper.findCurrentRule());
    }

    @Override
    public ResponseVO setRules(List<RuleVO> ruleVOS) {
        taskMapperWrapper.changeProcessor(ruleVOS);
        return ResponseVO.succeed(taskMapperWrapper.findCurrentRule());
    }
}