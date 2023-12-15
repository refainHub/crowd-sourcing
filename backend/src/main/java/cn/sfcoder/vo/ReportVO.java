package cn.sfcoder.vo;

import cn.sfcoder.annotation.UserId;
import cn.sfcoder.dto.ReportDTO;
import cn.sfcoder.po.enums.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/15 20:22
 * @Version: 1.0
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReportVO implements VO {

    Integer id;

    Integer userId;

    Integer taskId;

    List<MultipartFile> images;

    List<String> paths;

    List<String> toKeep;

    String note;

    String steps;

    String device;

    String name;

    String email;

    WorkStatus workStatus;

    Double star;

    Integer starNum;

    Double similarity;

    @UserId
    Integer annotationUserId;

    public ReportVO(Integer id, Integer userId, Integer taskId, List<String> paths, String note, String steps, String device, String email, WorkStatus workStatus) {
        this.id = id;
        this.userId = userId;
        this.taskId = taskId;
        this.paths = paths;
        this.note = note;
        this.steps = steps;
        this.device = device;
        this.email = email;
        this.workStatus = workStatus;
    }

    public ReportVO(Integer userId, Integer taskId, List<MultipartFile> images, String note, String steps, String device) {
        this.userId = userId;
        this.taskId = taskId;
        this.images = images;
        this.note = note;
        this.steps = steps;
        this.device = device;
    }

    @Override
    public ReportDTO toDTO() {
        return new ReportDTO(id, userId, taskId, images, paths, toKeep, note, steps, workStatus, null, email, device, star, starNum);
    }

    @Override
    public String toString() {
        return "ReportVO{" +
                "userId=" + userId +
                ", taskId=" + taskId +
                ", images=" + images +
                ", note='" + note + '\'' +
                ", steps='" + steps + '\'' +
                ", device='" + device + '\'' +
                '}';
    }

}
