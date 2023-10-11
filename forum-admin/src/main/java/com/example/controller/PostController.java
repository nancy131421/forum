package com.example.controller;

import com.example.dto.Result;
import com.example.pojo.Post;
import com.example.service.PostService;
import com.example.pojo.PostInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;


    //上传帖子
    @PostMapping("/up")
    public Result<String> insertPost(@RequestBody Post post) {
        return postService.insertPost(post);
    }


    //按时间排序(个人)
    @GetMapping("/time/{id}")
    public Result<List<Post>> timeSort(@PathVariable("id") Integer id) {
        return postService.timeSort(id);
    }


    //按热度排序(个人)
    @GetMapping("/hot/{id}")
    public Result<List<Post>> hotSort(@PathVariable("id") Integer id) {
        return postService.hotSort(id);
    }


    //获取全部帖子
    @GetMapping("/findAll")
    public Result<List<Post>> findAll() {
        return postService.findAll();
    }


    //获取热榜top10
    @GetMapping("/top10")
    public Result<List<Post>> top10() {
        return postService.top10();
    }


    //点赞
    @PutMapping("/good/{id}")
    public Result<Boolean> good(@PathVariable("id") Integer id) {
       return postService.good(id);
    }

    //收藏
    @PutMapping("/collect/{id}")
    public Result<Boolean> collect(@PathVariable("id") Integer id) {
        return postService.collect(id);
    }


    //获取收藏列表
    @GetMapping("/collect")
    public Result<List<Post>> getCollect() {
        return postService.getCollect();
    }


    //浏览量增加
    @PutMapping("/view/{id}")
    public void addView(@PathVariable("id") Integer id) {
        postService.addView(id);
    }


    //获取当前帖子流量信息
    @GetMapping("/info/{id}")
    public Result<PostInfoVO> findInfo(@PathVariable("id") Integer id) {
        return postService.findInfo(id);
    }
}
