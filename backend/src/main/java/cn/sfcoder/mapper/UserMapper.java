package cn.sfcoder.mapper;


import cn.sfcoder.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author Lenovo
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-11-17 22:35:58
* @Entity generator.domain.User
*/


@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
