package com.boss.trainee.rbac.entity.vo.roleVO;

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


}
