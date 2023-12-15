package cn.sfcoder.vo;

import cn.sfcoder.annotation.UserId;
import cn.sfcoder.dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/15 20:24
 * @Version: 1.0
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentVO implements VO {
    @UserId
    Integer id;

    Integer reportId;

    Integer userId;

    Double mark;

    String comment;

    String name;

    String email;

    @Override
    public CommentDTO toDTO() {
        return new CommentDTO(id, reportId, userId, mark, comment, name, email);
    }
}