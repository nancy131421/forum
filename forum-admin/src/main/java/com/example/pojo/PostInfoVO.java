package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostInfoVO {
    private Integer view;
    private Integer good;
    private Integer discuss;
    private Integer collect;
    //是否被点赞
    @Transient
    private Boolean isGood = false;
    //是否被收藏
    @Transient
    private Boolean isCollect = false;

}
