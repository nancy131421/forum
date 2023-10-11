package com.example.mapper;

import com.example.pojo.Post;
import com.example.pojo.PostInfoVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMapper {
    //插入帖子
    void insertPost(Post post);
    //按时间排序(个人)
    List<Post> timeSort(Integer id);
    //按热度排序(个人)
    List<Post> hotSort(Integer id);
    //按时间排序(全体)
    List<Post> findAll();
    //热榜top10
    List<Post> top10();
    //点赞
    void addGood(Integer id);
    //取消点赞
    void delGood(Integer id);
    //收藏
    void addCollect(Integer id);
    //取消收藏
    void delCollect(Integer id);
    //获取收藏列表
    List<Post> getCollect(int[] ids);
    //浏览数增加
    void addView(Integer id);
    //获取当前帖子流量信息
    PostInfoVO findInfo(Integer id);
    //评论数量加1
    void addDiscuss(Integer id);
}
