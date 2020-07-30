package com.boss.trainee.rbac.entity.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/29 20:01
 */
@Data
@ApiModel(description = "角色状态ID")
public class RoleStatusVO {


    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "用户ID")
    private Long uid;

    @ApiModelProperty(value = "角色状态")
    private Boolean status;
}
