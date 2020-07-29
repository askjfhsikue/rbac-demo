package com.boss.trainee.rbac.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/29 19:54
 */
@Data
public class RoleDTO {

    private Long roleId;

    private Long uid;

    private Long adminId;

    private String name;

    List<PermissionDTO> permissionDTOList;

}
