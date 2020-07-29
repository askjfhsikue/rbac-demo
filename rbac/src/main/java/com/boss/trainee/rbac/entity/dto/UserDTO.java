package com.boss.trainee.rbac.entity.dto;

import com.boss.trainee.rbac.entity.po.Role;
import com.boss.trainee.rbac.entity.po.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/24 15:08
 */
@Data
public class UserDTO implements UserDetails {

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色信息
     */
    private List<Role> roleList;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 写一个能直接使用user创建jwtUser的构造器
     *
     * @param user
     */
    public UserDTO(User user) {
        uid = user.getUid();
        username = user.getUsername();
        password = user.getPassword();
        authorities = user.getRoleList();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;

    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
