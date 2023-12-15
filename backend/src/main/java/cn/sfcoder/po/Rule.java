package cn.sfcoder.po;

import cn.sfcoder.dto.DTO;
import cn.sfcoder.dto.RuleDTO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/14 17:26
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("rule")
public class Rule implements PO {

    @TableField("id")
    private Integer id;

    @TableField("name")
    private String name;


    /**
     * 规则说明
     */
    @TableField("hint")
    private String hint;

    @Override
    public DTO toDTO() {
        RuleDTO ruleDTO=new RuleDTO();
        ruleDTO.setName(name);
        ruleDTO.setId(id);
        ruleDTO.setHint(hint);
        return ruleDTO;
    }

}