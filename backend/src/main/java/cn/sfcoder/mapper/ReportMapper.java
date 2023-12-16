package cn.sfcoder.mapper;

import cn.sfcoder.po.Report;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 20:12
 * @Version: 1.0
 */
@Mapper
@Repository
public interface ReportMapper extends BaseMapper<Report> {
    Report selectLastByUserIdAndTaskId(int userId, int taskId);
}