package com.boss.trainee.rbac.entity.vo.roleVO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/28 9:52
 */
@Data
@ApiModel(description = "角色参数")
public class RoleEditVO {

    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 被授权人id
     */
    private Long uid;


}
