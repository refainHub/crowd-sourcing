package cn.sfcoder.vo;

import cn.sfcoder.annotation.UserId;
import cn.sfcoder.dto.WorkDTO;
import cn.sfcoder.po.enums.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/15 20:13
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkVO implements VO{

    @UserId
    private Integer userId;

    private Integer taskId;

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
