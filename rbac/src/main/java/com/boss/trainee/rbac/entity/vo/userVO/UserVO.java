package com.boss.trainee.rbac.entity.vo.userVO;

import com.boss.trainee.rbac.entity.po.Role;
import lombok.Data;

import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/29 14:16
 */
@Data
public class UserVO {

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 用户名
     */
    private String username;
    /**
     * 角色
     */
    public List<Role> roleList;
}
