package com.boss.trainee.rbac.service;

import com.boss.trainee.rbac.entity.vo.RoleEditVO;

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
     * @param editVO
     * @return
     */
    boolean setRole(RoleEditVO editVO);

    /**
     * 删除某角色已有的权限
     * 删除的权限必须是授权人拥有的权限
     *
     * @param editVO
     * @return
     */
    boolean removeRole(RoleEditVO editVO);

    /**
     * 修改某个角色的状态
     * 被修改的的角色必须是授权人拥有的角色
     *
     * @param editVO
     * @return
     */
    boolean forbidRole(RoleEditVO editVO);
}
