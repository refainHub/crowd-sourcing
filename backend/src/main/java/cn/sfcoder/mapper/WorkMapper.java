package cn.sfcoder.mapper;

import cn.sfcoder.po.UserWorkList;
import cn.sfcoder.po.Work;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 20:09
 * @Version: 1.0
 */
@Mapper
@Repository
public interface WorkMapper extends BaseMapper<Work> {

    List<UserWorkList> findUsersTaskPartIn(List<Integer> taskIds);

    List<Integer> selectPartTasksByUserId(int userId);
}