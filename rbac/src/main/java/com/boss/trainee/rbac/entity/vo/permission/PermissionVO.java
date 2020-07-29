package com.boss.trainee.rbac.entity.vo.permission;

import lombok.Data;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/29 20:10
 */
@Data
public class PermissionVO {

    /**
     * 权限名
     */
    private String title;

    /**
     * 对应路径
     */
    private String url;
    /**
     * 状态
     */
    private Boolean status;
}
