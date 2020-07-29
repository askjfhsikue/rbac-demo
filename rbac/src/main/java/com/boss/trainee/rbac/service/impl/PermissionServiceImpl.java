package com.boss.trainee.rbac.service.impl;

import com.boss.trainee.rbac.dao.PermissionDAO;
import com.boss.trainee.rbac.entity.dto.PermissionDTO;
import com.boss.trainee.rbac.entity.dto.RolePermissionDTO;
import com.boss.trainee.rbac.entity.po.Permission;
import com.boss.trainee.rbac.entity.vo.permission.PermissionVO;
import com.boss.trainee.rbac.service.PermissionService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 14:10
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDAO permissionDAO;
    @Autowired
    private Mapper mapper;

    /**
     * 判断是否已存在该权限
     *
     * @param url
     * @return
     */
    private Permission getByURL(String url) {
        Example example = new Example(Permission.class);
        example.createCriteria().andEqualTo("url", url);
        return permissionDAO.selectOneByExample(example);
    }

    @Override
    public boolean insert(PermissionVO permissionVO) {
        Permission permission = mapper.map(permissionVO, Permission.class);
        if (getByURL(permission.getUrl()) != null) {
            return false;
        }
        Date date = new Date();
        permission.setCreateTime(date);
        permissionDAO.insert(permission);

        return true;
    }

    @Override
    public boolean edit(PermissionVO permissionVO) {
        Permission permission = mapper.map(permissionVO, Permission.class);
        if (getByURL(permission.getUrl()) == null) {
            return false;
        }
        Date date = new Date();
        permission.setUpdateTime(date);
        permissionDAO.updateByPrimaryKeySelective(permission);
        return false;
    }

    @Override
    public RolePermissionDTO pageGetRolePermission(Long roleId, Integer start, Integer length) {
        List<PermissionDTO> permissionDTOList = permissionDAO.pageGetRolePermission(roleId, start, length);
        RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();
        rolePermissionDTO.setPermissionDTOList(permissionDTOList);
        rolePermissionDTO.setRoleId(roleId);
        return rolePermissionDTO;
    }


}
