package cn.sfcoder.mapper;

import cn.sfcoder.po.Code;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author Lenovo
* @description 针对表【code】的数据库操作Mapper
* @createDate 2023-11-17 22:42:20
* @Entity cn.sfcoder.po.Code
*/
@Mapper
@Repository
public interface CodeMapper extends BaseMapper<Code> {

}




