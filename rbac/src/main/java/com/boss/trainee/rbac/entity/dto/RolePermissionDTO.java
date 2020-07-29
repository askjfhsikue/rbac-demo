package com.boss.trainee.rbac.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/26 19:56
 */
@Data
public class RolePermissionDTO {
    /**
     * 授权人id
     */
    private Long adminId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 授权人id
     */
    private Long permissionId;

    /**
     * 角色名
     */
    private String name;
    /**
     * 访问路径
     */
    private String url;

    /**
     * 所属权限
     */
    private List<PermissionDTO> permissionDTOList;

}
