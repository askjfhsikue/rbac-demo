package com.boss.trainee.rbac.service;

import com.boss.trainee.rbac.entity.dto.RoleDTO;
import com.boss.trainee.rbac.entity.vo.roleVO.RolePermissionEditVO;

import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 14:13
 */
public interface RoleService {

    /**
     * 分页获取角色id.角色名
     *
     * @param start
     * @param length
     * @return
     */
    List<RoleDTO> pageGetRole(Integer start, Integer length);

    /**
     * 获取指定用户的所有角色
     *
     * @return
     */
    List<RoleDTO> getUserRole();

    /**
     * 获取角色数量
     *
     * @return
     */
    Integer count();

    /**
     * 赋予某角色权限
     * 赋予的权限必须是授权人拥有的权限
     * 若该角色已有权限，则不操作；否则添加
     *
     * @param rolePermissionEditVO
     * @return
     */
    boolean addPermissions(RolePermissionEditVO rolePermissionEditVO);

    /**
     * 删除某角色的某个权限
     * 删除的权限必须是授权人拥有的权限
     * 若该角色已有权限，则删除；否则不操作
     *
     * @param rolePermissionEditVO
     * @return
     */
    boolean removePermission(RolePermissionEditVO rolePermissionEditVO);


}
