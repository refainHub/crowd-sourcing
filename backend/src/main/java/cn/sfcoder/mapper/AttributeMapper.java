package cn.sfcoder.mapper;

import cn.sfcoder.po.Attribute;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author Lenovo
* @description 针对表【attribute】的数据库操作Mapper
* @createDate 2023-11-17 22:54:38
* @Entity cn.sfcoder.po.Attribute
*/
@Mapper
@Repository
public interface AttributeMapper extends BaseMapper<Attribute> {
}


