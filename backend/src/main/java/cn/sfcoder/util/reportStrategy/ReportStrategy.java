package cn.sfcoder.util.reportStrategy;
import cn.sfcoder.dto.ReportDTO;
import cn.sfcoder.vo.VO;

import java.util.List;


/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 21:52
 * @Version: 1.0
 */


public abstract class ReportStrategy {

    public abstract List<VO> choose(int reportId, List<ReportDTO>reportDTOs);

}
