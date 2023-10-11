package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO extends Comment{
    // 昵称
    private String nickName;

    //头像
    private String headUrl;

    //个性签名
    private String signature;

    //点赞
    @Transient
    private Boolean isGood = false;

    //子评论数量
    @Transient
    private Integer sub = 0;

    //打开
    @Transient
    private Boolean isOpen = false;

    //子评论
    @Transient
    private List<ReplyVO> replyList;
}
