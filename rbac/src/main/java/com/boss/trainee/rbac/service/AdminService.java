package com.boss.trainee.rbac.service;

import java.util.Map;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 16:04
 */
public interface AdminService {

    /**
     * 通过用户id查询角色id
     *
     * @param uid
     * @return
     */
    Map<Long, Long> getRolesId(Long uid);

    /**
     * 给用户设置角色
     * 需要授权人拥有的权限才能赋予
     *
     * @param adminId
     * @param uid
     * @param roleId
     * @return
     */
    boolean setRole(Long adminId, Long uid, Long roleId);

    /**
     * 删除某角色已有的权限
     *
     * @param adminId
     * @param uid
     * @param roleId
     * @return
     */
    boolean removeRole(Long adminId, Long uid, Long roleId);
}
