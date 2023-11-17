package cn.sfcoder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.sfcoder.po.Attribute;
import cn.sfcoder.service.AttributeService;
import cn.sfcoder.mapper.AttributeMapper;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【attribute】的数据库操作Service实现
* @createDate 2023-11-17 22:54:38
*/
@Service
public class AttributeServiceImpl extends ServiceImpl<AttributeMapper, Attribute>
    implements AttributeService{

}




