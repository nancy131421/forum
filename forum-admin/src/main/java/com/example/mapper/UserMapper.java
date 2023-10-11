package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int sign(User user);
    User login(@Param("account") String account, @Param("password") String password);
}
