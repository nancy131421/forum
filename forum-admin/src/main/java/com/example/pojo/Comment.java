package com.example.pojo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    //评论id
    private Integer cid;
    //评论创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp createDate;
    //评论者id
    private Integer uid;
    //对应帖子id
    private Integer postId;
    //被回复者id，默认-1(没有被回复者)
    private Integer replyId;
    //最顶部评论id，默认-1(没有顶部评论)
    private Integer topId;
    //评论内容
    private String content;
    //点赞数量
    private Integer good;
}
