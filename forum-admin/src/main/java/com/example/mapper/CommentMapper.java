package com.example.mapper;

import com.example.pojo.Comment;
import com.example.pojo.CommentVO;
import com.example.pojo.ReplyVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    //插入评论
    void insert(Comment comment);

    //获取一级评论(热度)
    List<CommentVO> hotSort(Integer id);

    //获取一级评论(时间)
    List<CommentVO> timeSort(Integer id);

    //评论点赞和取消
    void doGood(@Param("step") Integer step, @Param("id") Integer id);

    //计算子评论数量
    int getSub(@Param("pid") Integer pid, @Param("tid") Integer tid);

    //查找子评论
    List<ReplyVO> findSub(@Param("pid") Integer pid, @Param("tid") Integer tid);
}
