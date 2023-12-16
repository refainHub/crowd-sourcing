package cn.sfcoder.util.reportStrategy;

import cn.sfcoder.dto.ReportDTO;
import cn.sfcoder.vo.VO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 22:26
 * @Version: 1.0
 */
@Component
public class BadStrategy extends ReportStrategy {

    @Override
    public List<VO> choose(int reportId, List<ReportDTO> reportDTOs) {
        reportDTOs.sort(Comparator.comparing(ReportDTO::getStar));
        List<VO> ans=new ArrayList<>();
        for (int i=0;i<Math.min(reportDTOs.size(),9);i++){
            ans.add(reportDTOs.get(i).toVO());
        }
        return ans;
    }

}