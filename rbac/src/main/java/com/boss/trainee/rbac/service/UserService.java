package com.boss.trainee.rbac.service;

import com.boss.trainee.rbac.entity.dto.UserDTO;
import com.boss.trainee.rbac.entity.vo.UserVO;

import java.util.List;


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

    /**
     * 新增用户,并赋予初始角色
     *
     * @param userDTO
     * @return
     */
    boolean add(UserDTO userDTO);

    /**
     * 分页获取用户信息
     *
     * @param start
     * @param length
     * @return
     */
    List<UserVO> pageGet(Integer start, Integer length);

    /**
     * 获取用户数量
     *
     * @return
     */
    Integer count();

}
