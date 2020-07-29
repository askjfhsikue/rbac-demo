package com.boss.trainee.rbac.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 14:38
 */
@Data
public class UserRoleDTO {

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 角色id
     */
    private List<Long> roleIds;


}
