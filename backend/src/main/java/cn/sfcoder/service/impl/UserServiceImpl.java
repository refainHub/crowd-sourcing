package cn.sfcoder.service.impl;

import cn.sfcoder.mapper.CodeMapper;
import cn.sfcoder.mapper.UserMapper;
import cn.sfcoder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/11/17 20:58
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    CodeMapper codeMapper;

    @Autowired
    AttributeMapper attributeMapper;

    @Override
    public ResponseVO register(UserDTO userDTO) {
        User userPO = userDTO.toPO();
        String email = userDTO.getEmail();
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        List<User> users = userMapper.selectByMap(map);
        if (users.size() > 0) {
            return ResponseVO.fail(UserHttpStatus.USER_REPEAT);
        } else {
            // 注册入user表格
            String salt = UUID.randomUUID().toString();
            userPO.setSalt(salt);
            userPO.setPasswd(MD5Util.md5(userPO.getPasswd(), salt));
            userMapper.insert(userPO);
            if (userPO.getUserIdentity().equals(UserIdentity.WORKER)) {
                // 注册入attribute表格
                Attribute attribute = new Attribute();
                attribute.setUserId(userPO.getId());
                attributeMapper.insert(attribute);
            }
            return ResponseVO.succeed();
        }
    }

    @Override
    public ResponseVO login(UserDTO userDTO) {
        String email = userDTO.getEmail();
        String passwd = userDTO.getPasswd();
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        List<User> users = userMapper.selectByMap(map);
        if (users.size() == 0) {
            return ResponseVO.fail(UserHttpStatus.USER_NOT_EXIST);
        }
        User user = users.get(0);
        if (!MD5Util.verify(passwd, user.getSalt(), user.getPasswd())) {
            return ResponseVO.fail(UserHttpStatus.PASSWD_ERROR);
        } else {
            // 生成token
            HashMap<String, Object> userMap = new HashMap<>();
            userMap.put("userId", user.getId());
            userMap.put("identity", user.getUserIdentity());
            String newToken = TokenUtil.createJWT(userMap);
            HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            httpServletResponse.setHeader("token", newToken);
            httpServletResponse.addHeader("Access-Control-Expose-Headers", "token");
            UserVO userVO = user.toDTO().toVO();

            // 设置最后一次登录信息
            user.setIp(IpUtil.getIpAddr(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()));
            user.setLastLogin(System.currentTimeMillis());
            userMapper.updateById(user);
            // 返回用户信息
            return ResponseVO.succeed(userVO);
        }
    }

    @Override
    public boolean codeJudge(String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        return codeMapper.deleteByMap(map) > 0;
    }

    @Override
    public UserIdentity getIdentityByUserId(int userId) {
        User user = userMapper.selectById(userId);
        if (user == null) return null;
        return user.getUserIdentity();
    }
}