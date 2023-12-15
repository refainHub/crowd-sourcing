package cn.sfcoder.po;

import cn.sfcoder.dto.WorkDTO;
import cn.sfcoder.po.enums.WorkStatus;
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
 * @Date: 2023/12/15 20:10
 * @Version: 1.0
 */
@Data
@TableName("work")
@AllArgsConstructor
@NoArgsConstructor
public class Work implements PO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("userId")
    private Integer userId;

    @TableField("taskId")
    private Integer taskId;

    @TableField("finish")
    private WorkStatus finish;

    @Override
    public WorkDTO toDTO() {
        WorkDTO workDTO = new WorkDTO();
        workDTO.setUserId(userId);
        workDTO.setTaskId(taskId);
        workDTO.setFinish(finish);
        return workDTO;
    }
}