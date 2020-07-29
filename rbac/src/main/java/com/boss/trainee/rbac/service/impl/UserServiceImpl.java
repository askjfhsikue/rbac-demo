package com.boss.trainee.rbac.service.impl;


import com.boss.trainee.rbac.dao.UserDAO;
import com.boss.trainee.rbac.dao.UserRoleDAO;
import com.boss.trainee.rbac.po.User;
import com.boss.trainee.rbac.po.UserRole;
import com.boss.trainee.rbac.service.UserService;
import com.boss.trainee.rbac.service.dto.UserDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/22 16:38
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;
    @Autowired
    private Mapper mapper;

    /**
     * 初始角色
     */
    private static final Long ROLE_ID = 3L;


    /**
     * 获取用户名和密码
     *
     * @param uid
     * @return
     */
    private User selectUser(Long uid) {
        return userDAO.selectByPrimaryKey(uid);
    }


    /**
     * 获取用户名、角色、权限。
     *
     * @param username
     * @return
     */
    @Override
    public UserDTO get(String username) {
        User user = userDAO.get(username);
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO(user);
        return userDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(UserDTO userDTO) {
        User user = mapper.map(userDTO, User.class);
        Date date = new Date();
        user.setCreateTime(date);
        userDAO.insert(user);
        UserRole userRole = new UserRole();
        userRole.setUid(user.getUid());
        userRole.setRoleId(ROLE_ID);
        userRoleDAO.insert(userRole);
        return true;
    }


}
