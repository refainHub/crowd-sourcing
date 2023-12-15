package cn.sfcoder.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/15 19:49
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("coworkers")
public class Coworkers {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("workId")
    private Integer workId;

    @TableField("coworkerId")
    private Integer coworkerId;


}