package com.boss.trainee.rbac.entity.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/28 9:52
 */
@Data
@ApiModel(description = "角色参数")
public class RoleEditVO {

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "被授权人ID")
    private Long uid;


}
