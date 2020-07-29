package com.boss.trainee.rbac.service.dto;

import lombok.Data;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 20:45
 */
@Data
public class PermissionDTO {

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
