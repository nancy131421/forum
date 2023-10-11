package com.example.pojo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    //帖子主键id
    private Integer id;
    //帖子创建者id
    private Integer createId;
    //用户昵称
    private String nickName;
    //用户头像地址
    private String headUrl;
    //个性签名
    private String signature;
    //是否被点赞
    @Transient
    private Boolean isGood = false;
    //是否被收藏
    @Transient
    private Boolean isCollect = false;
    //帖子创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp createDate;
    //帖子标题
    private String title;
    //帖子内容
    private String content;
    //帖子浏览量
    private Integer view;
    //帖子点赞量
    private Integer good;
    //帖子评论量
    private Integer discuss;
    //帖子收藏量
    private Integer collect;

}
