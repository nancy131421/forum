package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    private Integer id;
    
    private String account;
    
    private String nickName;
    
    private String password;
    
    private String signature;
    
    private String headUrl;

    
}

