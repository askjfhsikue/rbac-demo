package com.boss.trainee.rbac.entity.vo.user;

import com.boss.trainee.rbac.entity.po.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/29 14:16
 */
@Data
@ApiModel(description = "用户及角色")
public class UserVO {

    @ApiModelProperty(value = "用户ID")
    private Long uid;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "角色")
    public List<Role> roleList;
}
