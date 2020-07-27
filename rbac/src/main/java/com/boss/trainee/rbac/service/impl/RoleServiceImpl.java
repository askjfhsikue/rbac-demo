package com.boss.trainee.rbac.service.impl;

import com.boss.trainee.rbac.dao.RolePermissionDAO;
import com.boss.trainee.rbac.dao.UserDAO;
import com.boss.trainee.rbac.po.RolePermission;
import com.boss.trainee.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 16:03
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RolePermissionDAO rolePermissionDAO;


    @Override
    public boolean addPermissions(Long adminId, Long roleId, Long permissionId) {
        if (userDAO.getUserPermissionDTO(adminId, permissionId) == null) {
            return false;
        }
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        rolePermissionDAO.insert(rolePermission);
        return true;
    }

    @Override
    public boolean removePermission(Long adminId, Long roleId, Long permissionId) {

        return true;
    }
}
