package cn.sfcoder.po;

import cn.sfcoder.dto.TaskDTO;
import cn.sfcoder.po.enums.DeviceType;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/15 19:37
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("task")
public class Task implements PO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("userId")
    private Integer userId;

    @TableField("name")
    private String name;

    @TableField("number")
    private Integer number;

    @TableField("remain")
    private Integer remain;

    @TableField("tag")
    private Integer tag;

    @TableField("date")
    private Long date;

    @TableField(value = "aurl", updateStrategy = FieldStrategy.IGNORED)
    private String aurl;

    @TableField(value = "purl", updateStrategy = FieldStrategy.IGNORED)
    private String purl;

    @TableField("introduction")
    private String introduction;

    @TableField("level")
    private Integer level;

    @TableField("device")
    private DeviceType device;

    @Override
    public TaskDTO toDTO() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(id);
        taskDTO.setUserId(userId);
        taskDTO.setName(name);
        taskDTO.setNumber(number);
        taskDTO.setRemain(remain);
        taskDTO.setTag(tag);
        taskDTO.setDate(date);
        taskDTO.setAurl(aurl);
        taskDTO.setPurl(purl);
        taskDTO.setIntroduction(introduction);
        taskDTO.setLevel(level);
        taskDTO.setDevice(device);
        return taskDTO;
    }

    public Task(Integer userId, String name, Integer number, Integer remain, Integer tag, Long date, String aurl, String purl,String introduction) {
        this.userId = userId;
        this.name = name;
        this.number = number;
        this.remain = remain;
        this.tag = tag;
        this.date = date;
        this.aurl = aurl;
        this.purl = purl;
        this.introduction = introduction;
    }
}