package com.boss.trainee.rbac.entity.vo.roleVO;

import lombok.Data;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/29 19:59
 */
@Data
public class RolePermissionEditVO {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long permissionId;

}
