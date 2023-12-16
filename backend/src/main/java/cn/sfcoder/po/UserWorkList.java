package cn.sfcoder.po;

import lombok.Data;
import lombok.Setter;

import java.util.List;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/15 20:14
 * @Version: 1.0
 */
@Data
@Setter
public class UserWorkList {
    /**
     * 众包工人id
     */
    Integer userId;
    /**
     * 众包工人拥有的任务集合
     */
    List<Integer> taskId;

}