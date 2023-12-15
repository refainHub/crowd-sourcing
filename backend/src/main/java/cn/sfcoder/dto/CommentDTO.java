package cn.sfcoder.dto;

import cn.sfcoder.po.Comment;
import cn.sfcoder.vo.CommentVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: refain
 * @Description: 众包工人对报告的评分和评价
 * @Date: 2023/12/15 20:25
 * @Version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CommentDTO implements DTO {
    Integer id;

    Integer reportId;

    Integer userId;

    Double mark;

    String comment;

    String name;

    String email;
    @Override
    public Comment toPO() {
        return new Comment(id,reportId,userId,mark,comment,name,email);
    }

    @Override
    public CommentVO toVO() {
        return new CommentVO(id,reportId,userId,mark,comment,name,email);
    }
}