package com.boss.trainee.rbac.service.impl;


import com.boss.trainee.rbac.dao.UserDAO;
import com.boss.trainee.rbac.po.User;
import com.boss.trainee.rbac.service.UserService;
import com.boss.trainee.rbac.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/22 16:38
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;


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


}
