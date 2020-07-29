package com.boss.trainee.rbac.service;

import com.boss.trainee.rbac.entity.dto.RolePermissionDTO;
import com.boss.trainee.rbac.entity.vo.permission.PermissionVO;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 14:04
 */
public interface PermissionService {

    /**
     * 往权限表里增加权限
     * 若权限存在则直接返回
     *
     * @param permissionVO
     * @return
     */
    boolean insert(PermissionVO permissionVO);

    /**
     * 修改权限表中权限
     *
     * @param permissionVO
     * @return
     */
    boolean edit(PermissionVO permissionVO);


    /**
     * 分页获取指定角色的权限
     *
     * @param roleId
     * @param start
     * @param length
     * @return
     */
    RolePermissionDTO pageGetRolePermission(Long roleId, Integer start, Integer length);

}
