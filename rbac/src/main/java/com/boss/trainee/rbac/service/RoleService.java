package com.boss.trainee.rbac.service;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 14:13
 */
public interface RoleService {

    /**
     * 赋予某角色权限
     *
     * @param adminId
     * @param roleId
     * @param permissionId
     * @return
     */
    boolean addPermissions(Long adminId, Long roleId, Long permissionId);

    /**
     * 删除某角色的某个权限
     *
     * @param adminId
     * @param roleId
     * @param permissionId
     * @return
     */
    boolean removePermission(Long adminId, Long roleId, Long permissionId);


}
