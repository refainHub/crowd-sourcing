package cn.sfcoder.dto;

import cn.sfcoder.po.Attribute;
import cn.sfcoder.vo.AttributeVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 22:34
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AttributeDTO implements DTO {

    Integer id;

    Integer userId;

    List<Integer> device;

    List<Integer> preferTask;

    @Override
    public Attribute toPO() {
        Attribute attribute = new Attribute();
        for (int i : device) {
            switch (i) {
                case 0:
                    attribute.setWindows(10);
                    break;
                case 1:
                    attribute.setLinux(10);
                    break;
                case 2:
                    attribute.setMac(10);
                    break;
                case 3:
                    attribute.setAndroid(10);
                    break;
                case 4:
                    attribute.setIos(10);
                    break;
                case 5:
                    attribute.setHarmonyos(10);
                    break;
            }
        }
        for (int i : preferTask) {
            switch (i) {
                case 1:
                    attribute.setPerformance(10);
                    break;
                case 0:
                    attribute.setFunctional(10);
                    break;
                case 2:
                    attribute.setBug(10);
                    break;
            }
        }
        attribute.setUserId(userId);
        return attribute;
    }

    @Override
    public AttributeVO toVO() {
        return new AttributeVO(id, userId, new ArrayList<>(), new ArrayList<>());
    }
}
