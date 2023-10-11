package com.example.utils;

public class RedisConstant {
    //帖子点赞头
    public static final String POST_GOOD = "post:good:";

    //登录用户头
    public static final String LOGIN_USER = "login:user:";

    //用户token过期时间
    public static final Long LOGIN_USER_TTL = 1800L;

    //热榜top10tou
    public static final String HOT_TOP10 = "hot:top10";

    //热榜过期时间
    public static final Long HOT_TOP10_TTL = 600L;

    //收藏key
    public static final String POST_COLLECT = "post:collect:";

    //帖子评论点赞key
    public static final String POST_COMMENT_GOOD = "post:comment:good:";
}
