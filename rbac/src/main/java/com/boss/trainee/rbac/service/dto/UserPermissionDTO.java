package com.boss.trainee.rbac.service.dto;

import lombok.Data;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 19:22
 */
@Data
public class UserPermissionDTO {

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 权限id
     */
    private Long permissionId;

}
