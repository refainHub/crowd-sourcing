package cn.sfcoder.vo;

import cn.sfcoder.annotation.UserId;
import cn.sfcoder.dto.TaskDTO;
import cn.sfcoder.po.enums.DeviceType;
import cn.sfcoder.po.enums.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/15 19:43
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskVO implements VO {

    Integer id;

    @UserId
    Integer userId;

    String name;

    Integer number;

    Integer remain;

    Integer tag;

    Long date;

    // apk url
    String aurl;

    // picture url
    String purl;

    String introduction;

    MultipartFile apk;

    MultipartFile pdf;

    Boolean deleteApk;

    Boolean deletePdf;

    WorkStatus workStatus;

    Integer reportNum;

    Integer level;

    DeviceType device;


    public TaskDTO toDTO() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(id);
        taskDTO.setUserId(userId);
        taskDTO.setName(name);
        taskDTO.setNumber(number);
        taskDTO.setTag(tag);
        taskDTO.setDate(date);
        taskDTO.setApk(apk);
        taskDTO.setPdf(pdf);
        taskDTO.setAurl(aurl);
        taskDTO.setPurl(purl);
        taskDTO.setDeleteApk(deleteApk);
        taskDTO.setDeletePdf(deletePdf);
        taskDTO.setIntroduction(introduction);
        taskDTO.setLevel(level);
        taskDTO.setDevice(device);
        return taskDTO;
    }
}
