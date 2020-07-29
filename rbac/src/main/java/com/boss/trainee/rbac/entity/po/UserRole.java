package com.boss.trainee.rbac.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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
    /**
     * 创建时间
     */
    @Column
    private Date createTime;
    /**
     * 修改时间
     */
    @Column
    private Date updateTime;
}
