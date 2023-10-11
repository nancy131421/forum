package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.utils.Constant.FAIl_CODE;
import static com.example.utils.Constant.SUCCESS_CODE;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T t) {
        Result<T> r = new Result<>();
        r.code = SUCCESS_CODE;
        r.data = t;
        r.msg = "操作成功";
        return r;
    }

    public static <T> Result<T> success(String s) {
        Result<T> r = new Result<>();
        r.code = SUCCESS_CODE;
        r.msg = s;
        return r;
    }

    public static <T> Result<T> fail(String s) {
        Result<T> r = new Result<>();
        r.code = FAIl_CODE;
        r.msg = s;
        return r;
    }
}
