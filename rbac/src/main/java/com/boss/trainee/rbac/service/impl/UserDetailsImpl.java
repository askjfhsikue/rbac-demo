package com.boss.trainee.rbac.service.impl;

import com.boss.trainee.rbac.entity.dto.UserDTO;
import com.boss.trainee.rbac.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/22 17:32
 */
@Service
@Slf4j
public class UserDetailsImpl implements UserDetailsService {
    @Autowired
    private UserService userService;


    private final String USER_NOT_EXIST = "用户名不存在";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户的用户名: {}", username);
        UserDTO userDTO = userService.get(username);
        userDTO.getAuthorities();
        // 判断用户是否存在
        if (userDTO == null) {
            throw new UsernameNotFoundException(USER_NOT_EXIST);
        }

        return userDTO;
    }
}
