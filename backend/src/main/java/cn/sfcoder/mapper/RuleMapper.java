package cn.sfcoder.mapper;

import cn.sfcoder.po.Rule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/14 17:25
 * @Version: 1.0
 */

@Mapper
@Repository
public interface RuleMapper extends BaseMapper<Rule> {
}