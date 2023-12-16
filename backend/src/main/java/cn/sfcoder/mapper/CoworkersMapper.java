package cn.sfcoder.mapper;

import cn.sfcoder.po.Coworkers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 20:16
 * @Version: 1.0
 */
@Mapper
@Repository
public interface CoworkersMapper extends BaseMapper<Coworkers> {

}