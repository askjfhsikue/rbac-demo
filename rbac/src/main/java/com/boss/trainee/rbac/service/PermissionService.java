package com.boss.trainee.rbac.service;

import com.boss.trainee.rbac.entity.dto.PermissionDTO;
import com.boss.trainee.rbac.entity.dto.RolePermissionDTO;

import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 14:04
 */
public interface PermissionService {

    /**
     * 往权限表里增加权限
     * 若权限存在则直接返回
     *
     * @param permissionDTO
     * @return
     */
    boolean insert(PermissionDTO permissionDTO);

    /**
     * 修改权限表中权限
     *
     * @param permissionDTO
     * @return
     */
    boolean edit(PermissionDTO permissionDTO);

    /**
     * 分页获取指定角色的权限
     *
     * @param roleId
     * @param start
     * @param length
     * @return
     */
    List<RolePermissionDTO> pageGetRolePermission(Long roleId, Integer start, Integer length);

}
