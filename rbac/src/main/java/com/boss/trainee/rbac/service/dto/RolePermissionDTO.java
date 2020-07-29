package com.boss.trainee.rbac.service.dto;

import lombok.Data;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/26 19:56
 */
@Data
public class RolePermissionDTO {
    /**
     * 角色名
     */
    private String name;
    /**
     * 访问路径
     */
    private String url;
}
