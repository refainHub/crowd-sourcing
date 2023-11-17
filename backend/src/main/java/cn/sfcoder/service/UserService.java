package cn.sfcoder.service;

import cn.sfcoder.dto.UserDTO;
import cn.sfcoder.po.enums.UserIdentity;

/**
 * @Author:     refain
 * @Description:  用户服务
 * @Date:   2023/11/17 20:35
 * @Version: 1.0
 */
public interface UserService {
    ResponseVO register(UserDTO userDTO);

    ResponseVO login(UserDTO userDTO);

    boolean codeJudge(String code);

    UserIdentity getIdentityByUserId(int userId);
}