package cn.sfcoder.mapper;

import cn.sfcoder.po.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/16 20:17
 * @Version: 1.0
 */
@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    List<Comment> selectCommentByReportId(Integer reportId);

    List<Comment> selectComment(Integer reportId, Integer userId);
}