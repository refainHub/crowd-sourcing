package cn.sfcoder.dto;

import cn.sfcoder.po.Report;
import cn.sfcoder.po.enums.WorkStatus;
import cn.sfcoder.vo.ReportVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/15 20:21
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO implements DTO {

    Integer id;
    Integer userId;
    Integer taskId;
    List<MultipartFile> images;
    List<String> paths;
    List<String> toKeep;
    String note;
    String steps;

    WorkStatus workStatus;

    String name;

    String email;

    String device;

    Double star;

    Integer starNum;

    public ReportDTO(Integer userId, Integer taskId, List<MultipartFile> images, String note, String steps, String device) {
        this.userId = userId;
        this.taskId = taskId;
        this.images = images;
        this.note = note;
        this.steps = steps;
        this.device = device;
    }

    public ReportDTO(Integer id, Integer userId, Integer taskId, List<MultipartFile> images, List<String> toKeep, String note, String steps, String device) {
        this.id = id;
        this.userId = userId;
        this.taskId = taskId;
        this.images = images;
        this.note = note;
        this.steps = steps;
        this.device = device;
        this.toKeep = toKeep;
    }

    public ReportDTO(Integer id, Integer userId, Integer taskId, String note, String steps, String device) {
        this.id = id;
        this.userId = userId;
        this.taskId = taskId;
        this.note = note;
        this.steps = steps;
        this.device = device;
    }


    @Override
    public Report toPO() {
        return new Report(id, userId, taskId, note, steps, device, star, starNum);
    }

    @Override
    public ReportVO toVO() {
        return new ReportVO(id, userId, taskId, images,paths, toKeep,note, steps, device, name,email, workStatus,star,starNum,null,null);
    }
}