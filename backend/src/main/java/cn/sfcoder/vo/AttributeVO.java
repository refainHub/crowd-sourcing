package cn.sfcoder.vo;

import cn.sfcoder.annotation.UserId;
import cn.sfcoder.dto.AttributeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 22:33
 * @Version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttributeVO implements VO {

    Integer id;

    @UserId
    Integer userId;

    List<Integer> device;

    List<Integer> preferTask;


    @Override
    public AttributeDTO toDTO() {
        return new AttributeDTO(null, userId, device, preferTask);
    }

    @Override
    public String toString() {
        return "AttributeVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", device=" + device +
                ", preferTask=" + preferTask +
                '}';
    }
}