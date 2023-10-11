package com.example.service;

import com.example.dto.Result;
import com.example.pojo.User;

public interface UserService {
    Result<String> sign(User user);
    Result<User> Login(String account, String password);
}
