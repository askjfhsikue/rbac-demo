package com.boss.trainee.rbac.po;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/22 16:23
 */
@Data
@Table(name = "role")
public class Role implements GrantedAuthority {
    /**
     * 角色id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名
     */
    @Column
    private String name;
    /**
     * 状态
     */
    @Column
    private Boolean status;
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

    /**
     * 权限信息
     */
    @Transient
    private List<Permission> permissionList;

    @Override
    public String getAuthority() {
        return name;
    }
}
