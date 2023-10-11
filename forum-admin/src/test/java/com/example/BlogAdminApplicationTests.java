package com.example;

import com.example.pojo.Post;
import com.example.utils.RedisConstant;
import com.example.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
class BlogAdminApplicationTests {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void test1() {
        String s = UUID.randomUUID() + "";
        System.out.println(s);
        System.out.println(s.replaceAll("-",""));
        System.out.println(s);
    }

    @Test
    void test2() {
        Post post = new Post();
        post.setCreateDate(new Timestamp(new Date().getTime()));
        System.out.println(post.getCreateDate());
    }

    @Test
    void test3() {
        String s = "abcd";
        StringBuilder sb = new StringBuilder();
        for (int i = s.length()-1; i >=0; i--) {
            sb.append(s.charAt(i));
        }
        System.out.println(sb);
    }

    @Test
    void test4() {
//        Set<Object> k1 = redisUtil.sGet("k1");
//        System.out.println(k1);
//        for (Object o : k1) {
//            System.out.println((int) o + 1);
//        }
        Integer i = (int) redisUtil.sGetSetSize(RedisConstant.POST_COLLECT + "12");
        System.out.println(i);
    }

    @Test
    void test5() {
//        redisUtil.zAddIfAbsent("k1","a",(double) new Date().getTime());
//        redisUtil.zRemove("k1","a");
//        System.out.println("添加成功");
//        System.out.println(redisUtil.zGetKeyLength("k1"));
//        System.out.println(redisUtil.zHasKey("k1","b"));

        Set<Object> objects = redisUtil.zReverseGetKeys("post:collect:12", 0L, -1L);
        for (Object o : objects) {
            System.out.println(o);
        }
    }
}
