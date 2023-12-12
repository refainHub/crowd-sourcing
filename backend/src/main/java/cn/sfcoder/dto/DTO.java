package cn.sfcoder.dto;

import cn.sfcoder.po.PO;
import cn.sfcoder.vo.VO;

public interface DTO {
    PO toPO();

    VO toVO();

}