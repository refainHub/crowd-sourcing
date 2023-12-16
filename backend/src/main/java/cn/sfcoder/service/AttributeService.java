package cn.sfcoder.service;

import cn.sfcoder.dto.AttributeDTO;
import cn.sfcoder.po.Attribute;
import cn.sfcoder.vo.ResponseVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Lenovo
* @description 针对表【attribute】的数据库操作Service
* @createDate 2023-11-17 22:54:38
*/

public interface AttributeService {
    Attribute ifIn(int userId);
    ResponseVO init(AttributeDTO attributeDTO);
}
