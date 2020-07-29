package com.boss.trainee.rbac.service;

import com.boss.trainee.rbac.vo.RoleEditVO;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 14:13
 */
public interface RoleService {

    /**
     * 赋予某角色权限
     *
     * @param editVO
     * @return
     */
    boolean addPermissions(RoleEditVO editVO);

    /**
     * 删除某角色的某个权限
     *
     * @param editVO
     * @return
     */
    boolean removePermission(RoleEditVO editVO);


}
