package com.boss.trainee.rbac.service;

import com.boss.trainee.rbac.service.dto.UserDTO;


/**
 * @author: Jianbinbing
 * @Date: 2020/7/22 16:36
 */
public interface UserService {

    /**
     * 通过用户名获取用户信息及权限
     *
     * @param username
     * @return
     */
    UserDTO get(String username);


}
