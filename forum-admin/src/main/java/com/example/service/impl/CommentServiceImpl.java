package com.example.service.impl;

import com.example.dto.Result;
import com.example.dto.UserHolder;
import com.example.mapper.CommentMapper;
import com.example.mapper.PostMapper;
import com.example.pojo.Comment;
import com.example.pojo.CommentVO;
import com.example.pojo.ReplyVO;
import com.example.service.CommentService;
import com.example.utils.RedisConstant;
import com.example.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private PostMapper postMapper;


    //插入评论
    @Override
    public Result<String> insert(Comment comment) {
        //插入评论
        comment.setCreateDate(new Timestamp(new Date().getTime()));
        comment.setUid(UserHolder.getUser().getId());
        commentMapper.insert(comment);

        //评论数量加1
        postMapper.addDiscuss(comment.getPostId());

        return Result.success("评论成功");
    }


    //查找一级评论，热度
    @Override
    public Result<List<CommentVO>> hotSort(Integer id) {
        List<CommentVO> list = commentMapper.hotSort(id);
        findSub(list);
        findGood(list);
        return Result.success(list);
    }


    //查找一级评论，时间
    @Override
    public Result<List<CommentVO>> timeSort(Integer id) {
        List<CommentVO> list = commentMapper.timeSort(id);
        findSub(list);
        findGood(list);
        return Result.success(list);
    }


    //评论点赞
    @Override
    public Result<Boolean> doGood(Integer id) {
        String key = RedisConstant.POST_COMMENT_GOOD + id;
        //向redis的set添加用户id
        if (redisUtil.sAdd(key,UserHolder.getUser().getId())) {
            //数据库赞+1
            commentMapper.doGood(1,id);
            return Result.success(true);
        } else {
            //添加用户id失败，则取消点赞
            commentMapper.doGood(-1,id);
            //移除用户id
            redisUtil.sRem(key,UserHolder.getUser().getId());
            return Result.success(false);
        }
    }


    // 查找子评论
    @Override
    public Result<List<ReplyVO>> findSub(Integer pid, Integer tid) {
        List<ReplyVO> list = commentMapper.findSub(pid,tid);
        findSubGood(list);
        return Result.success(list);
    }


    //确定被点赞评论
    public void findGood(List<CommentVO> list) {
        if (UserHolder.getUser() != null) {
            for (CommentVO commentVO : list) {
                String key = RedisConstant.POST_COMMENT_GOOD + commentVO.getCid();
                if (redisUtil.sHasKey(key,UserHolder.getUser().getId())) {
                    commentVO.setIsGood(true);
                }
            }
        }
    }

     //确定子评论被点赞
    public void findSubGood(List<ReplyVO> list) {
        if (UserHolder.getUser() != null) {
            for (ReplyVO replyVO : list) {
                String key = RedisConstant.POST_COMMENT_GOOD + replyVO.getCid();
                if (redisUtil.sHasKey(key,UserHolder.getUser().getId())) {
                    replyVO.setIsGood(true);
                }
            }
        }
    }


    //查找子评论数量
    public void findSub(List<CommentVO> list) {
        for (CommentVO commentVO : list) {
            commentVO.setSub(commentMapper.getSub(commentVO.getPostId(),commentVO.getCid()));
        }
    }
}
