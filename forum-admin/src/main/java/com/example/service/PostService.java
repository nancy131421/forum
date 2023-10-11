package com.example.service;

import com.example.dto.Result;
import com.example.pojo.Post;
import com.example.pojo.PostInfoVO;

import java.util.List;

public interface PostService {
    Result<String> insertPost(Post post);
    Result<List<Post>> timeSort(Integer id);
    Result<List<Post>> hotSort(Integer id);
    Result<List<Post>> findAll();
    Result<List<Post>> top10();
    Result<Boolean> good(Integer id);
    Result<Boolean> collect(Integer id);
    Result<List<Post>> getCollect();
    void addView(Integer id);
    Result<PostInfoVO> findInfo(Integer id);
}
