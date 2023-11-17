package cn.sfcoder.dto;


import cn.sfcoder.po.User;
import cn.sfcoder.po.enums.UserIdentity;
import cn.sfcoder.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO implements DTO {

    int id;

    String email;

    String passwd;

    String salt;

    UserIdentity userIdentity;

    String name;

    private Long lastLogin;

    private String ip;

    public UserDTO(String email, String passwd, UserIdentity userIdentity, String name) {
        this.email = email;
        this.passwd = passwd;
        this.userIdentity = userIdentity;
        this.name = name;
    }

    public UserDTO(int id, UserIdentity userIdentity) {
        this.id = id;
        this.userIdentity = userIdentity;
    }

    public UserDTO() {

    }

    public User toPO() {
        return new User(email, passwd, userIdentity,name);
    }

    public UserVO toVO() {
        UserVO userVO = new UserVO();
        userVO.setEmail(email);
        userVO.setUserIdentity(userIdentity);
        userVO.setName(name);
        userVO.setLastLogin(lastLogin);
        userVO.setId(id);
        return userVO;
    }
}