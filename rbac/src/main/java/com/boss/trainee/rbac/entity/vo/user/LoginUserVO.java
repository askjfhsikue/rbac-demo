package com.boss.trainee.rbac.entity.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/26 14:27
 */
@Data
@ApiModel(description = "登录用户")
public class LoginUserVO {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;
}
