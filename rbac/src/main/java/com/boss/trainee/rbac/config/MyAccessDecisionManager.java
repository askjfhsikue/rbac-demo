package com.boss.trainee.rbac.config;

import com.boss.trainee.rbac.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 决策器
 * MyAccessDecisionManager 类实现了AccessDecisionManager接口，
 * AccessDecisionManager是由AbstractSecurityInterceptor调用的，它负责鉴定用户是否有访问对应资源（方法或URL）的权限。
 *
 * @author: Jianbinbing
 * @Date: 2020/7/27 16:08
 */
@Component
@Slf4j
public class MyAccessDecisionManager implements AccessDecisionManager {


    /**
     * 通过传递的参数来决定用户是否有访问对应受保护对象的权限
     *
     * @param authentication   包含了当前的用户信息，包括拥有的权限。这里的权限来源就是前面登录时UserDetailsService中设置的authorities。
     * @param object           就是FilterInvocation对象，可以得到request等web资源
     * @param configAttributes configAttributes是本次访问需要的权限
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (null == configAttributes || 0 >= configAttributes.size()) {
            return;
        } else {
            String needRole;
            String temp = String.valueOf(authentication.getAuthorities());
            List<String> roles = new ArrayList<>();
            try {
                roles = JacksonUtils.JsonToBean(temp, List.class);
            } catch (IOException e) {
                e.printStackTrace();
            }


            for (Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext(); ) {
                needRole = iter.next().getAttribute();
                for (int i = 0; i < roles.size(); i++) {
                    log.info("needRole:{},role:{}", needRole, roles.get(0));
                    boolean b = StringUtils.containsIgnoreCase(temp, needRole.trim());
                    if (b) {
                        return;
                    }
                }
            }
            throw new AccessDeniedException("当前访问没有权限");
        }

    }

    /**
     * 表示此AccessDecisionManager是否能够处理传递的ConfigAttribute呈现的授权请求
     */
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    /**
     * 表示当前AccessDecisionManager实现是否能够为指定的安全对象（方法调用或Web请求）提供访问控制决策
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}

