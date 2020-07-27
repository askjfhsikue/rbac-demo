package com.boss.trainee.rbac.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/22 16:27
 */
@Data
@Table(name = "permission")
public class Permission {
    /**
     * 权限id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 权限名
     */
    @Column
    private String title;
    /**
     * 访问路径
     */
    @Column
    private String url;

    /**
     * 状态
     */
    @Column
    private Boolean status;
}
