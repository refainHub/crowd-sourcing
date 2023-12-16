package cn.sfcoder.vo;

import cn.sfcoder.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 22:12
 * @Version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClusterVO implements VO{

    List<List<Integer>> result;

    List<String> path;

    @Override
    public DTO toDTO() {
        return null;
    }
}