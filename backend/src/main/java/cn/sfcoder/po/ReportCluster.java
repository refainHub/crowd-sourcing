package cn.sfcoder.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: refain
 * @Description:  报告集
 * @Date: 2023/12/16 20:15
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("report_cluster")
public class ReportCluster {

    @TableField("taskId")
    private Integer taskId;

    @TableField("cluster_set")
    private String clusterSet;
}