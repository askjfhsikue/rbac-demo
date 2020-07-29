package com.boss.trainee.rbac.config;


import com.boss.trainee.rbac.dao.PermissionDAO;
import com.boss.trainee.rbac.entity.dto.RolePermissionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * FilterInvocationSecurityMetadataSource接口有3个方法：
 * boolean supports(Class<?> clazz)：指示该类是否能够为指定的方法调用或Web请求提供ConfigAttributes。
 * Collection<ConfigAttribute> getAllConfigAttributes()：Spring容器启动时自动调用, 一般把所有请求与权限的对应关系也要在这个方法里初始化, 保存在一个属性变量里。
 * Collection<ConfigAttribute> getAttributes(Object object)：当接收到一个http请求时, filterSecurityInterceptor会调用的方法. 参数object是一个包含url信息的HttpServletRequest实例.
 * 这个方法要返回请求该url所需要的所有权限集合。
 *
 * @author: Jianbinbing
 * @Date: 2020/7/27 16:08
 */
@Component
@Slf4j
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionDAO permissionDAO;

    /**
     * 每一个资源所需要的角色 Collection<ConfigAttribute>决策器会用到
     */
    private static HashMap<String, Collection<ConfigAttribute>> map = null;


    /**
     * 返回请求的资源需要的角色
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (null == map) {
            try {
                loadResourceDefine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        log.info("request:{}", request);
        for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ) {
            String url = it.next();
            if (new AntPathRequestMatcher(url).matches(request)) {
                log.info("map.role:{}", map.get(url));
                return map.get(url);
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * 初始化 所有资源 对应的角色
     */
    public void loadResourceDefine() throws Exception {
        map = new HashMap<>(16);
        //权限资源 和 角色对应的表  也就是 角色权限 中间表

        List<RolePermissionDTO> rolePermissons = permissionDAO.getRolePermissionDTO();

        //某个资源 可以被哪些角色访问
        for (RolePermissionDTO rolePermission : rolePermissons) {

            String url = rolePermission.getUrl();
            String roleName = rolePermission.getName();
            ConfigAttribute role = new SecurityConfig(roleName);

            if (map.containsKey(url)) {
                map.get(url).add(role);
            } else {
                List<ConfigAttribute> list = new ArrayList<>();
                list.add(role);
                map.put(url, list);
            }
        }
    }


}
