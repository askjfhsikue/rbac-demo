package com.boss.trainee.rbac.entity.vo.roleVO;

import lombok.Data;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/29 20:01
 */
@Data
public class RoleStatusVO {
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
     * 状态
     */
    private Boolean status;
}
