package com.boss.trainee.rbac.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/22 16:25
 */
@Data
@Table(name = "user_role")
public class UserRole {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户id
     */
    @Column
    private Long uid;

    /**
     * 角色id
     */
    @Column
    private Long roleId;
}
