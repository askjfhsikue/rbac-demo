package com.boss.trainee.rbac.service;

import com.boss.trainee.rbac.service.dto.PermissionDTO;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 14:04
 */
public interface PermissionService {

    /**
     * 给角色增加权限
     *
     * @param permissionDTO
     * @return
     */
    boolean insert(PermissionDTO permissionDTO);
}
