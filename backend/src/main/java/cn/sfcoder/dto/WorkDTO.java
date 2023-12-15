package cn.sfcoder.dto;

import cn.sfcoder.po.Work;
import cn.sfcoder.po.enums.WorkStatus;
import cn.sfcoder.vo.WorkVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/15 20:11
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkDTO implements DTO {

    private Integer userId;

    private Integer taskId;

    private WorkStatus finish;

    public WorkDTO(Integer userId,Integer taskId){
        this(userId,taskId,null);
    }

    @Override
    public Work toPO() {
        Work work = new Work();
        work.setUserId(userId);
        work.setTaskId(taskId);
        work.setFinish(finish);
        return work;
    }

    @Override
    public WorkVO toVO() {
        WorkVO work = new WorkVO();
        work.setUserId(userId);
        work.setTaskId(taskId);
        work.setFinish(finish);
        return work;
    }
}