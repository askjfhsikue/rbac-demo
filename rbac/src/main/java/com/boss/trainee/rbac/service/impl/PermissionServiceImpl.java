package com.boss.trainee.rbac.service.impl;

import com.boss.trainee.rbac.dao.PermissionDAO;
import com.boss.trainee.rbac.po.Permission;
import com.boss.trainee.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 14:10
 */
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDAO permissionDAO;


    @Override
    public boolean insertList(List<Permission> permissionList) {

        permissionDAO.insertList(permissionList);

        return true;
    }
}
