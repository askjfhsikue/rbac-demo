package com.boss.trainee.rbac.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/22 16:29
 */
@Data
@Table(name = "role_permission")
public class RolePermission {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 权限id
     */
    @Column
    private Long permissionId;
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
