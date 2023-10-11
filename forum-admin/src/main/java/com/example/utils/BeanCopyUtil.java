package com.example.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtil {
    private BeanCopyUtil() {
    }

    //单个bean的复制
    public static <O,T> T beanCopy(O source, Class<T> target) {
        T result = null;
        try {
            result = target.newInstance();
            BeanUtils.copyProperties(source,result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    //bean集合的复制
    public static <O,T> List<T> beanListCopy(List<O> source, Class<T> target) {
        return source.stream()
                .map(o -> beanCopy(o,target))
                .collect(Collectors.toList());
    }
}
