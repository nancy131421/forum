package com.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.dto.Result;
import com.example.dto.UserHolder;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import com.example.utils.IsStrNull;
import com.example.utils.RedisConstant;
import com.example.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.utils.RedisConstant.LOGIN_USER_TTL;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;


    //注册
    @Override
    public Result<String> sign(User user) {
        if (IsStrNull.isnull(user.getAccount())) {
            return Result.fail("账户不能为空");
        }
        if (IsStrNull.isnull(user.getNickName())) {
            return Result.fail("昵称不能为空");
        }
        if (IsStrNull.isnull(user.getPassword())) {
            return Result.fail("密码不能为空");
        }
        if (IsStrNull.isnull(user.getSignature())) {
            user.setSignature("这个人很懒，什么都没留下");
        }
        userMapper.sign(user);
        return Result.success("注册成功");
    }


    //登录
    @Override
    public Result<User> Login(String account,String password) {
        //用户的key
        String key = RedisConstant.LOGIN_USER + account;
        User loginUser;
        //若存在key
        if (redisUtil.hasKey(key)) {
            //redis中寻找
            String userStr = redisUtil.get(key).toString();
            loginUser= JSON.parseObject(userStr, User.class);
        }else {
            //不存在key
            //数据库查找
            loginUser = userMapper.login(account.trim(),password.trim());
            //不存在用户
            if (loginUser == null) return Result.fail("账号或密码错误");

            //存入redis
            String userStr = JSON.toJSONString(loginUser);
            //redis存储用户信息
            redisUtil.set(key,userStr);
            //设置token有效期
            redisUtil.expire(key,LOGIN_USER_TTL);
        }
        UserHolder.saveUser(loginUser);
        return Result.success(loginUser);
    }
}
