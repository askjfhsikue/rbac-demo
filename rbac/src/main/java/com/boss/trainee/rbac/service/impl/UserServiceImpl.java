package com.boss.trainee.rbac.service.impl;


import com.boss.trainee.rbac.dao.UserDAO;
import com.boss.trainee.rbac.dao.UserRoleDAO;
import com.boss.trainee.rbac.entity.dto.UserDTO;
import com.boss.trainee.rbac.entity.po.User;
import com.boss.trainee.rbac.entity.po.UserRole;
import com.boss.trainee.rbac.entity.vo.userVO.UserVO;
import com.boss.trainee.rbac.service.UserService;
import com.boss.trainee.rbac.utils.DozerUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

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
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Date date = new Date();
        user.setCreateTime(date);
        userDAO.insert(user);
        UserRole userRole = new UserRole();
        userRole.setUid(user.getUid());
        userRole.setRoleId(ROLE_ID);
        userRoleDAO.insert(userRole);
        return true;
    }

    @Override
    public List<UserVO> pageGet(Integer start, Integer length) {
        start = (start - 1) * length;
        List<UserVO> userVOS = DozerUtils.listToList(userDAO.pageGet(start, length), UserVO.class);
        return userVOS;
    }

    @Override
    public Integer count() {

        Example example = new Example(User.class);
        Integer count = userDAO.selectCountByExample(example);
        return count;
    }


}
