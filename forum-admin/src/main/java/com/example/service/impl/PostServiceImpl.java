package com.example.service.impl;

import com.example.dto.Result;
import com.example.dto.UserHolder;
import com.example.mapper.PostMapper;
import com.example.pojo.Post;
import com.example.service.PostService;
import com.example.utils.BeanCopyUtil;
import com.example.utils.RedisConstant;
import com.example.utils.RedisUtil;
import com.example.pojo.PostInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private RedisUtil redisUtil;


    //发布帖子
    @Override
    public Result<String> insertPost(Post post) {
        post.setCreateDate(new Timestamp(new Date().getTime()));
        post.setCreateId(UserHolder.getUser().getId());
        postMapper.insertPost(post);
        return Result.success("发布成功");
    }


    //个人中心，按时间排序
    @Override
    public Result<List<Post>> timeSort(Integer id) {
        List<Post> postList = postMapper.timeSort(id);
        goodAndCollect(postList);
        return Result.success(postList);
    }


    //个人中心，按热度排序
    @Override
    public Result<List<Post>> hotSort(Integer id) {
        List<Post> postList = postMapper.hotSort(id);
        goodAndCollect(postList);
        return Result.success(postList);
    }


    //查找所有帖子
    @Override
    public Result<List<Post>> findAll() {
        List<Post> postList = postMapper.findAll();
        //用户登录后
        if (UserHolder.getUser() != null) {
            goodAndCollect(postList);
        }
        return Result.success(postList);
    }



    //热榜top10
    @Override
    public Result<List<Post>> top10() {
        List<Post> top10;
        if (redisUtil.hasKey(RedisConstant.HOT_TOP10)) {
            //从redis中获取热榜
            top10 = BeanCopyUtil.beanListCopy(redisUtil.lGet(RedisConstant.HOT_TOP10,0,-1), Post.class);
        } else  {
            //redis中不存在热榜缓存
            //从数据库中获取热榜
            top10 = postMapper.top10();
            for (Post post : top10) {
                redisUtil.lSet(RedisConstant.HOT_TOP10,post);
            }
            //设置缓存过期时间
            redisUtil.expire(RedisConstant.HOT_TOP10,RedisConstant.HOT_TOP10_TTL);
        }
        goodAndCollect(top10);
        return Result.success(top10);
    }


    //点赞
    @Override
    public Result<Boolean> good(Integer id) {
        //设置key
        String goodKey = RedisConstant.POST_GOOD + UserHolder.getUser().getId();
        //判断集合中是否存在uid
        //不存在，点赞
        if (redisUtil.sAdd(goodKey,id)) {
            postMapper.addGood(id);
            return Result.success(true);
        } else {
            //存在，取消点赞
            postMapper.delGood(id);
            redisUtil.sRem(goodKey,id);
            return Result.success(false);
        }
    }


    //收藏
    @Override
    public Result<Boolean> collect(Integer id) {
        //设置key
        String key = RedisConstant.POST_COLLECT + UserHolder.getUser().getId();
        //是否收藏改帖子
        if (redisUtil.zAddIfAbsent(key,id,(double) new Date().getTime())) {
            postMapper.addCollect(id);
            return Result.success(true);
        } else {
            redisUtil.zRemove(key,id);
            postMapper.delCollect(id);
            return Result.success(false);
        }
    }


    //获取用户的收藏列表
    @Override
    public Result<List<Post>> getCollect() {
        String key = RedisConstant.POST_COLLECT + UserHolder.getUser().getId();
        //获取收藏id长度
        int i = (int) redisUtil.zGetKeyLength(key);
        int[] ids = new int[i];
        //从redis获取收藏帖子的id
        Set<Object> set = redisUtil.zReverseGetKeys(key,0L,-1L);
        int j = 0;
        for (Object o : set) {
            ids[j] = (int) o;
            j++;
        }
        List<Post> collect = null;
        //数据库中查询收藏列表
        if (ids.length != 0) {
            collect = postMapper.getCollect(ids);
            goodAndCollect(collect);
        }
        return Result.success(collect);
    }

    @Override
    public void addView(Integer id) {
        if (UserHolder.getUser() != null) {
            postMapper.addView(id);
        }
    }


    //当前帖子流量信息
    @Override
    public Result<PostInfoVO> findInfo(Integer id) {
        PostInfoVO postInfoVO = postMapper.findInfo(id);
        if (UserHolder.getUser() != null) {
            String goodKey = RedisConstant.POST_GOOD + UserHolder.getUser().getId();
            String collectKey = RedisConstant.POST_COLLECT + UserHolder.getUser().getId();
            if (redisUtil.sHasKey(goodKey,id)) {
                postInfoVO.setIsGood(true);
            }
            if (redisUtil.zHasKey(collectKey,id) != null) {
                postInfoVO.setIsCollect(true);
            }
        }
        return Result.success(postInfoVO);
    }


    //帖子是否被用户点赞或收藏
    public void goodAndCollect(List<Post> postList) {
        if (UserHolder.getUser() != null) {
            String goodKey = RedisConstant.POST_GOOD + UserHolder.getUser().getId();
            String collectKey = RedisConstant.POST_COLLECT + UserHolder.getUser().getId();
            for (Post post : postList) {
                if (redisUtil.sHasKey(goodKey,post.getId())) {
                    post.setIsGood(true);
                }
                if (redisUtil.zHasKey(collectKey,post.getId()) != null) {
                    post.setIsCollect(true);
                }
            }
        }
    }
}
