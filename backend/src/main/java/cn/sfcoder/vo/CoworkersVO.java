package cn.sfcoder.vo;

import cn.sfcoder.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 22:13
 * @Version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoworkersVO implements VO{

    List<UserVO> coworkers;

    @Override
    public DTO toDTO() {
        return null;
    }
}