package com.boss.trainee.rbac.service.impl;

import com.boss.trainee.rbac.dao.RoleDAO;
import com.boss.trainee.rbac.dao.RolePermissionDAO;
import com.boss.trainee.rbac.dao.UserDAO;
import com.boss.trainee.rbac.entity.dto.RoleDTO;
import com.boss.trainee.rbac.entity.dto.RolePermissionDTO;
import com.boss.trainee.rbac.entity.po.Role;
import com.boss.trainee.rbac.entity.po.RolePermission;
import com.boss.trainee.rbac.entity.vo.role.RolePermissionEditVO;
import com.boss.trainee.rbac.service.RoleService;
import com.boss.trainee.rbac.utils.JwtTokenUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private HttpServletRequest request;


    @Override
    public List<RoleDTO> pageGetRole(Integer start, Integer length) {
        start = (start - 1) * length;
        List<RoleDTO> roleVOS = roleDAO.pageGet(start, length);
        return roleVOS;

    }

    @Override
    public List<RoleDTO> getUserRole() {
        Long uid = JwtTokenUtils.getUid(request);
        return roleDAO.getUserRole(uid);
    }

    @Override
    public Integer count() {
        Example example = new Example(Role.class);
        Integer count = roleDAO.selectCountByExample(example);
        return count;
    }

    @Override
    public boolean addPermissions(RolePermissionEditVO rolePermissionEditVO) {
        Long adminId = JwtTokenUtils.getUid(request);
        RolePermissionDTO rolePermissionDTO = mapper.map(rolePermissionEditVO, RolePermissionDTO.class);
        rolePermissionDTO.setAdminId(adminId);
        Long permissionId = rolePermissionEditVO.getPermissionId();
        Long roleId = rolePermissionEditVO.getRoleId();
        //判断用户是否具有该权限
        if (userDAO.getUserPermissionDTO(adminId, permissionId) == null) {
            return false;
        }
        Long id = rolePermissionDAO.get(roleId, permissionId);
        if (id != null) {
            return false;
        }
        RolePermission rolePermission = mapper.map(rolePermissionDTO, RolePermission.class);
        Date date = new Date();
        rolePermission.setCreateTime(date);
        rolePermissionDAO.insert(rolePermission);
        return true;
    }

    @Override
    public boolean removePermission(RolePermissionEditVO rolePermissionEditVO) {
        Long adminId = JwtTokenUtils.getUid(request);
        Long roleId = rolePermissionEditVO.getRoleId();
        Long permissionId = rolePermissionEditVO.getPermissionId();
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
