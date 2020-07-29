package com.boss.trainee.rbac.entity.vo;

import lombok.Data;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/28 9:52
 */
@Data
public class RoleEditVO {
    /**
     * 授权人id
     */
    private Long adminId;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 被授权人id
     */
    private Long uid;
    /**
     * 权限id
     */
    private Long permissionId;
    /**
     * 状态
     */
    private Boolean status;

}
