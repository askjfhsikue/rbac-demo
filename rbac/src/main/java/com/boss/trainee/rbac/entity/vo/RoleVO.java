package com.boss.trainee.rbac.entity.vo;

import com.boss.trainee.rbac.entity.dto.PermissionDTO;
import lombok.Data;

import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/29 14:43
 */
@Data
public class RoleVO {

    private Long roleId;

    private String name;

    List<PermissionDTO> permissionDTOList;

}
