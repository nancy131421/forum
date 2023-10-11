package com.example.controller;

import com.example.dto.Result;
import com.example.pojo.Comment;
import com.example.pojo.CommentVO;
import com.example.pojo.ReplyVO;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //插入评论
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody Comment comment) {
        return commentService.insert(comment);
    }


    //获取一级评论，热度
    @GetMapping("/hotSort/{id}")
    public Result<List<CommentVO>> hotSort(@PathVariable("id") Integer id) {
        return commentService.hotSort(id);
    }


    //获取一级评论，时间
    @GetMapping("/timeSort/{id}")
    public Result<List<CommentVO>> timeSort(@PathVariable("id") Integer id) {
        return commentService.timeSort(id);
    }

    //评论点赞
    @PutMapping("/doGood/{id}")
    public Result<Boolean> addGood(@PathVariable("id") Integer id) {
        return commentService.doGood(id);
    }


    //查找子评论
    @GetMapping("/findSub/{pid}/{tid}")
    public Result<List<ReplyVO>> findSub(@PathVariable("pid") Integer pid, @PathVariable("tid") Integer tid) {
        return commentService.findSub(pid,tid);
    }
}
