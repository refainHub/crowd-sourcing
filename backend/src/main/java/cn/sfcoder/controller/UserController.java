package cn.sfcoder.controller;

import cn.sfcoder.annotation.CheckStatus;
import cn.sfcoder.dto.UserDTO;
import cn.sfcoder.po.enums.UserIdentity;
import cn.sfcoder.service.AttributeService;
import cn.sfcoder.service.RuleService;
import cn.sfcoder.service.UserService;
import cn.sfcoder.util.CheckData;
import cn.sfcoder.vo.AttributeVO;
import cn.sfcoder.vo.ResponseVO;
import cn.sfcoder.vo.RuleVOS;
import cn.sfcoder.vo.UserVO;
import cn.sfcoder.vo.http.UserHttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/6 19:45
 * @Version: 1.0
 */
@RestController
@RequestMapping("/collect/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AttributeService attributeService;

    @Autowired
    RuleService ruleService;

    @CheckStatus(insert = CheckStatus.InsertMode.NONE)
    @GetMapping("/tryLogin")
    public ResponseVO tryLogin() {
        return ResponseVO.succeed();
    }

    @PostMapping("/register")
    public ResponseVO register(@RequestBody UserVO userVO) {
        if (CheckData.EmailCheck(userVO.getEmail())
                || CheckData.StringCheck(userVO.getPasswd(), 250)
                || CheckData.EnumCheck(userVO.getUserIdentity())) {
            return ResponseVO.fail(UserHttpStatus.DATA_ERROR);
        }
        if (userVO.getUserIdentity() == UserIdentity.ADMINISTRATOR) {
            if (CheckData.StringCheck(userVO.getCode(), 18)) {
                return ResponseVO.fail(UserHttpStatus.DATA_ERROR);
            }
            if (!userService.codeJudge(userVO.getCode())) {
                return ResponseVO.fail(UserHttpStatus.CODE_ERROR);
            }
        }
        UserDTO userDTO = userVO.toDTO();
        return userService.register(userDTO);
    }

    @PostMapping("/login")
    public ResponseVO login(@RequestBody UserVO userVO) {
        if (CheckData.EmailCheck(userVO.getEmail())
                || CheckData.StringCheck(userVO.getPasswd(), 250)) {
            return ResponseVO.fail(UserHttpStatus.DATA_ERROR);
        }
        UserDTO userDTO = userVO.toDTO();
        // 根据lastLogin是否存在来判断是否是第一次登陆，不需要调用IfIn方法
        return userService.login(userDTO);
    }

    @CheckStatus(identity = UserIdentity.WORKER)
    @PostMapping("/init")
    public ResponseVO init(@RequestBody AttributeVO attributeVO) {
        return attributeService.init(attributeVO.toDTO());
    }

    @GetMapping("/getRules")
    public ResponseVO getRules() {
        return ruleService.getRules();
    }

    @PostMapping("/setRules")
    public ResponseVO setRules(@RequestBody RuleVOS ruleVOS) {
        return ruleService.setRules(ruleVOS.getRuleVOS());
    }
}
