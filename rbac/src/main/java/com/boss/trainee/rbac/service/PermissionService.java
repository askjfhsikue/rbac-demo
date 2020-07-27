package com.boss.trainee.rbac.service;

import com.boss.trainee.rbac.po.Permission;

import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 14:04
 */
public interface PermissionService {

    boolean insertList(List<Permission> permissionList);
}
