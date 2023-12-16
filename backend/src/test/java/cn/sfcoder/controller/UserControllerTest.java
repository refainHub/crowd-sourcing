package cn.sfcoder.controller;


import cn.sfcoder.BackendApplication;
import cn.sfcoder.mapper.CodeMapper;
import cn.sfcoder.mapper.UserMapper;
import cn.sfcoder.po.Code;
import cn.sfcoder.po.enums.UserIdentity;
import cn.sfcoder.service.AttributeService;
import cn.sfcoder.service.UserService;
import cn.sfcoder.vo.AttributeVO;
import cn.sfcoder.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;




/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 22:47
 * @Version: 1.0
 */


@SpringBootTest(classes = BackendApplication.class)
public class UserControllerTest {
    @Autowired
    UserController userController;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CodeMapper codeMapper;

    @Autowired
    UserService userService;

    @Autowired
    AttributeService attributeService;

    @Transactional
    @Test
    void testRegisterDeliver() {
        UserVO userVO = new UserVO("1123", "123456", null, UserIdentity.DELIVER, "dxy");
        assert userController.register(userVO).getCode() == 4004;
        UserVO userVO2 = new UserVO("1123@smail.nju.edu.cn", "123456", null, UserIdentity.DELIVER, "dxy");
        assert userController.register(userVO2).getCode() == 4000;
////        Map<String,Object> map=new HashMap<>();
//        map.put("email","1123@smail.nju.edu.cn");
//        userMapper.deleteByMap(map);
    }

    @Transactional
    @Test
    void testRegisterAdmin() {
        UserVO userVO = new UserVO("213@qq.com", "123456", "abc", UserIdentity.ADMINISTRATOR, "dxy");
        assert userController.register(userVO).getCode() == 4005;
        codeMapper.insert(new Code("abc"));
        assert userController.register(userVO).getCode() == 4000;
//        Map<String, Object> map = new HashMap<>();
//        map.put("email", "213@qq.com");
//        userMapper.deleteByMap(map);
    }

    @Transactional
    @Test
    void testLogin() {
        UserVO userVO = new UserVO("213@qq.com", "123456", "abc", UserIdentity.WORKER, "dxy");
        userController.register(userVO);
        assert userController.login(userVO).getCode() == 4000;
//        Map<String, Object> map = new HashMap<>();
//        map.put("email", "213@qq.com");
//        userMapper.deleteByMap(map);
    }

    @Transactional
    @Test
    void testInit() {
        UserController userController = new UserController();
        userController.userService = userService;
        userController.attributeService = attributeService;

        UserVO userVO = new UserVO("213@qq.com", "123456", "abc", UserIdentity.WORKER, "dxy");
        userController.register(userVO);
        HashMap<String, Object> map = new HashMap<>();
        map.put("email", "213@qq.com");
        int userId = userMapper.selectByMap(map).get(0).getId();
        AttributeVO attributeVO = new AttributeVO();
        attributeVO.setUserId(userId);
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(5);
        attributeVO.setDevice(arrayList);
        arrayList.clear();
        arrayList.add(0);
        arrayList.add(2);
        attributeVO.setPreferTask(arrayList);
        assert userController.init(attributeVO).getCode() == 4000;
    }
}