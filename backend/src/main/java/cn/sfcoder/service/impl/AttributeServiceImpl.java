package cn.sfcoder.service.impl;

import cn.sfcoder.dto.AttributeDTO;
import cn.sfcoder.vo.ResponseVO;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.sfcoder.po.Attribute;
import cn.sfcoder.service.AttributeService;
import cn.sfcoder.mapper.AttributeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author refain
* @description 针对表【attribute】的数据库操作Service实现
* @createDate 2023-11-17 22:54:38
*/
@Service
public class AttributeServiceImpl implements AttributeService {


    @Autowired
    AttributeMapper attributeMapper;

    @Deprecated
    @Override
    public Attribute ifIn(int userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        List<Attribute> attributeList = attributeMapper.selectByMap(map);
        if (attributeList.size() == 0)
            return null;
        else
            return attributeList.get(0);
    }

    @Override
    public ResponseVO init(AttributeDTO attributeDTO) {
        UpdateWrapper<Attribute> updateWrapper = new UpdateWrapper<>();
        Map<String, Object> updateByUserId = new HashMap<>();
        updateByUserId.put("userId", attributeDTO.getUserId());
        updateWrapper.allEq(updateByUserId);
        attributeMapper.update(attributeDTO.toPO(), updateWrapper);
        return ResponseVO.succeed();
    }
}



