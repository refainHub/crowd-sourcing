package cn.sfcoder.vo;

import cn.sfcoder.dto.DTO;
import cn.sfcoder.dto.RuleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/6 19:48
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleVO implements VO {
    Integer id;

    String hint; // 规则说明

    String name; // 规则名称

    Boolean valid; // 规则是否启用

    Map<String, Object> extra; // 规则其他属性

    @Override
    public DTO toDTO() {
        return new RuleDTO(null,null,hint);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleVO ruleVO = (RuleVO) o;
        return Objects.equals(id, ruleVO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}