package com.boss.trainee.rbac.entity.vo.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/29 20:10
 */
@Data
@ApiModel(description = "权限类")
public class PermissionVO {

    @ApiModelProperty(value = "权限名")
    private String title;

    @ApiModelProperty(value = "权限路径")
    private String url;

    @ApiModelProperty(value = "权限状态")
    private Boolean status;
}
