package com.boss.trainee.rbac.service;

import com.boss.trainee.rbac.service.dto.PermissionDTO;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 14:04
 */
public interface PermissionService {

    /**
     * 增加权限
     * 若权限存在则直接返回
     *
     * @param permissionDTO
     * @return
     */
    boolean insert(PermissionDTO permissionDTO);

    /**
     * 修改权限
     *
     * @param permissionDTO
     * @return
     */
    boolean edit(PermissionDTO permissionDTO);

}
