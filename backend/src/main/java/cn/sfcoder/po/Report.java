package cn.sfcoder.po;

import cn.sfcoder.dto.ReportDTO;
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
 * @Date: 2023/12/15 20:18
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("report")
public class Report implements PO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("userId")
    private Integer userId;

    @TableField("taskId")
    private Integer taskId;

    @TableField("note")
    private String note;

    @TableField("steps")
    private String steps;

    @TableField("device")
    private String device;

    @TableField("star")
    private Double star;

    @TableField("starNum")
    private Integer starNum;

    public Report(Integer userId, Integer taskId, String note, String steps, String device) {
        this.userId = userId;
        this.taskId = taskId;
        this.note = note;
        this.steps = steps;
        this.device = device;
    }

    public Report(Integer id, Integer userId, Integer taskId, String note, String steps, String device) {
        this.id = id;
        this.userId = userId;
        this.taskId = taskId;
        this.note = note;
        this.steps = steps;
        this.device = device;
    }

    @Override
    public ReportDTO toDTO() {
        return new ReportDTO(id, userId, taskId, null, null, null, note, steps, null, null, null, device, star, starNum);
    }
}
