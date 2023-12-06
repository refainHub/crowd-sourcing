package cn.sfcoder.po;

import cn.sfcoder.annotation.Judge;
import cn.sfcoder.dto.DTO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName attribute
 */
@TableName(value ="attribute")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attribute implements PO {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("userId")
    private Integer userId;

    @Judge(desc = "windows平台参与")
    @TableField("windows")
    private Integer windows;

    @Judge(desc = "mac平台参与")
    @TableField("mac")
    private Integer mac;

    @Judge(desc = "android平台参与")
    @TableField("android")
    private Integer android;

    @Judge(desc = "ios平台参与")
    @TableField("ios")
    private Integer ios;

    @Judge(desc = "harmony平台参与")
    @TableField("harmony")
    private Integer harmonyos;

    @Judge(desc = "linux平台参与")
    @TableField("linux")
    private Integer linux;

    @Judge(desc = "性能任务参与")
    @TableField("performance")
    private Integer performance;

    @Judge(desc = "功能任务参与")
    @TableField("functional")
    private Integer functional;

    @Judge(desc = "稳定性任务参与")
    @TableField("bug")
    private Integer bug;

    @Judge(mul = 0.2, desc = "性能任务能力")
    @TableField("performanceAbility")
    private Double performanceAbility;

    @Judge(mul = 0.2, desc = "稳定性任务能力")
    @TableField("bugAbility")
    private Double bugAbility;

    @Judge(mul = 0.2, desc = "功能任务能力")
    @TableField("functionalAbility")
    private Double functionalAbility;

    @Judge(desc = "用户信誉")
    @TableField("credibility")
    private Double credibility;

    @Override
    public DTO toDTO() {
        return null;
    }

    public Integer getSum() {
        return functional + bug + performance;
    }
}