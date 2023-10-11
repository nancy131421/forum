package com.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyVO {
    //当前评论id
    private Integer cid;
    //用户头像
    private String headUrl;
    //用户名
    private String nickName;
    //个性签名
    private String signature;
    //用户id
    private Integer uid;
    //评论日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp createDate;
    //被回复者头像
    private String rHead;
    //被回复者名称
    private String rName;
    //被回复者id
    private Integer rid;
    //最顶部评论id
    private Integer topId;
    //回复内容
    private String content;
    //被回复者个性签名
    private String rsignature;
    //点赞
    private Integer good;
    //是否被点赞
    private Boolean isGood = false;
}
