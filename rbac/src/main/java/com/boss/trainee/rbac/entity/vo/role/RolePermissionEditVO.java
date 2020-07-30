package com.boss.trainee.rbac.entity.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/29 19:59
 */
@Data
@ApiModel(description = "角色参数")
public class RolePermissionEditVO {

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "权限ID")
    private Long permissionId;

}
