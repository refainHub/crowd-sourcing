package cn.sfcoder.po;

import cn.sfcoder.annotation.Judge;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName attribute
 */
@TableName(value ="attribute")
@Data
public class Attribute implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField("userId")
    private Integer userid;

    /**
     * 
     */
    @Judge(desc = "windows平台参与")
    @TableField("windows")
    private Integer windows;

    /**
     * 
     */
    private Integer linux;

    /**
     * 
     */
    private Integer mac;

    /**
     * 
     */
    private Integer android;

    /**
     * 
     */
    private Integer ios;

    /**
     * 
     */
    private Integer harmony;

    /**
     * 
     */
    private Integer performance;

    /**
     * 
     */
    private Integer functional;

    /**
     * 
     */
    private Integer bug;

    /**
     * 
     */
    private Double performanceability;

    /**
     * 
     */
    private Double bugability;

    /**
     * 
     */
    private Double functionalability;

    /**
     * 
     */
    private Double credibility;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}