package com.example.service;

import com.example.dto.Result;
import com.example.pojo.Comment;
import com.example.pojo.CommentVO;
import com.example.pojo.ReplyVO;

import java.util.List;

public interface CommentService {
    //插入评论
    Result<String> insert(Comment comment);

    //获取一级评论，热度
    Result<List<CommentVO>> hotSort(Integer id);

    //获取一级评论，时间
    Result<List<CommentVO>> timeSort(Integer id);

    //评论点赞
    Result<Boolean> doGood(Integer id);

    //获取子评论
    Result<List<ReplyVO>> findSub(Integer pid, Integer tid);
}
