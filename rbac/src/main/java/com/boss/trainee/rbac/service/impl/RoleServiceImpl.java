package com.boss.trainee.rbac.service.impl;

import com.boss.trainee.rbac.dao.RoleDAO;
import com.boss.trainee.rbac.dao.RolePermissionDAO;
import com.boss.trainee.rbac.dao.UserDAO;
import com.boss.trainee.rbac.entity.po.Role;
import com.boss.trainee.rbac.entity.po.RolePermission;
import com.boss.trainee.rbac.entity.vo.RoleEditVO;
import com.boss.trainee.rbac.entity.vo.RoleVO;
import com.boss.trainee.rbac.service.RoleService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

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
    private RoleDAO roleDAO;
    @Autowired
    private Mapper mapper;


    @Override
    public List<RoleVO> pageGetRole(Integer start, Integer length) {
        start = (start - 1) * length;
        List<RoleVO> roleVOS = roleDAO.pageGet(start, length);
        return roleVOS;

    }

    @Override
    public Integer count() {
        Example example = new Example(Role.class);
        Integer count = roleDAO.selectCountByExample(example);
        return count;
    }

    @Override
    public boolean addPermissions(RoleEditVO editVO) {
        Long adminId = editVO.getAdminId();
        Long permissionId = editVO.getPermissionId();
        Long roleId = editVO.getRoleId();
        //判断用户是否具有该权限
        if (userDAO.getUserPermissionDTO(adminId, permissionId) == null) {
            return false;
        }
        Long id = rolePermissionDAO.get(roleId, permissionId);
        if (id != null) {
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
