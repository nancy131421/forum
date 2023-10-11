package com.example.controller;

import com.example.dto.Result;
import com.example.dto.UserHolder;
import com.example.pojo.User;
import com.example.service.UserService;
import com.example.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    public String headUrl = Constant.DEFAULT_PATH;


    //注册
    @PostMapping("/sign")
    public Result<String> sign(@RequestBody User user) {
        user.setHeadUrl(headUrl);
        return userService.sign(user);
    }


    //上传头像
    @PostMapping("/upload")
    public void upload(MultipartFile file) {
        if (file != null) {
            String pathName;
            //设置文件名称
            if (UserHolder.getUser() != null) {
                pathName = UserHolder.getUser().getHeadUrl();
            }else {
                pathName = UUID.randomUUID().toString().replace("-","");
            }
            try {
                //将文件存放到指定位置
                file.transferTo(new File(Constant.UPLOAD_PLACE + pathName + ".jpg"));
                //设置头像路径
                headUrl =  pathName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //登录
    @GetMapping("/login")
    public Result<User> login(String account, String password) {
        return userService.Login(account,password);
    }
}
