package cn.sfcoder.dto;

import cn.sfcoder.po.PO;
import cn.sfcoder.vo.RuleVO;
import cn.sfcoder.vo.VO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/6 19:50
 * @Version: 1.0
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RuleDTO implements DTO {

    Integer id;
    String name;
    String hint; // 规则说明

    @Override
    public PO toPO() {
        return null;
    }

    @Override
    public VO toVO() {
        RuleVO ruleVO=new RuleVO();
        ruleVO.setHint(hint);
        ruleVO.setName(name);
        ruleVO.setId(id);
        return ruleVO;
    }
}