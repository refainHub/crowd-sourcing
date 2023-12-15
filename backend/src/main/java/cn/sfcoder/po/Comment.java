package cn.sfcoder.po;

import cn.sfcoder.dto.CommentDTO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/15 20:26
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("comment")
public class Comment implements PO {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    @TableField("reportId")
    Integer reportId;

    @TableField("userId")
    Integer userId;

    @TableField("mark")
    Double mark;

    @TableField("comment")
    String comment;

    String name;

    String email;

    @Override
    public CommentDTO toDTO() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setReportId(reportId);
        commentDTO.setUserId(userId);
        commentDTO.setMark(mark);
        commentDTO.setComment(comment);
        commentDTO.setName(name);
        commentDTO.setEmail(email);
        return commentDTO;
    }
}