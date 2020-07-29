package com.boss.trainee.rbac.utils;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/29 16:06
 */
@Component
public class DozerUtils {
    @Autowired
    private static Mapper mapper;

    /**
     * list对象转list对象
     *
     * @param sourceList
     * @param targetClass
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> List<T> listToList(List<S> sourceList, Class<T> targetClass) {
        List targetList = new ArrayList();
        for (S s :
                sourceList) {
            targetList.add(mapper.map(s, targetClass));
        }
        return targetList;
    }

}
