package com.boss.trainee.rbac.service.impl;

import com.boss.trainee.rbac.dao.RolePermissionDAO;
import com.boss.trainee.rbac.dao.UserDAO;
import com.boss.trainee.rbac.po.RolePermission;
import com.boss.trainee.rbac.service.RoleService;
import com.boss.trainee.rbac.vo.RoleEditVO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    @Autowired
    private Mapper mapper;


    @Override
    public boolean addPermissions(RoleEditVO editVO) {
        Long adminId = editVO.getAdminId();
        Long permissionId = editVO.getPermissionId();
        if (userDAO.getUserPermissionDTO(adminId, permissionId) == null) {
            return false;
        }
        RolePermission rolePermission = mapper.map(editVO, RolePermission.class);
        Date date = new Date();
        rolePermission.setCreateTime(date);
        rolePermissionDAO.insert(rolePermission);
        return true;
    }

    @Override
    public boolean removePermission(RoleEditVO editVO) {
        Long adminId = editVO.getAdminId();
        Long roleId = editVO.getRoleId();
        Long permissionId = editVO.getPermissionId();
        if (userDAO.getUserPermissionDTO(adminId, permissionId) == null) {
            return false;
        }
        Long id = rolePermissionDAO.get(roleId, permissionId);
        if (id == null) {
            return false;
        }
        rolePermissionDAO.deleteByPrimaryKey(id);
        return true;
    }


}
