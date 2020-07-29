package com.boss.trainee.rbac.filter;

import com.boss.trainee.rbac.entity.dto.UserDTO;
import com.boss.trainee.rbac.entity.vo.userVO.LoginUserVO;
import com.boss.trainee.rbac.utils.JacksonUtils;
import com.boss.trainee.rbac.utils.JwtTokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 登录时进行拦截，并返回token
 *
 * @author: Jianbinbing
 * @Date: 2020/7/26 14:23
 */
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        // 从输入流中获取到登录的信息
        try {
            LoginUserVO loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUserVO.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 成功验证后调用的方法
     * 如果验证成功，就生成token并返回
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {

        // 调用getPrincipal()方法会返回一个实现了UserDetails接口的对象
        UserDTO jwtUser = (UserDTO) authResult.getPrincipal();
        List<String> roles = new ArrayList<>();
        String role = "";
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            role = authority.getAuthority();
            roles.add(role);
        }

        String token = JwtTokenUtils.createToken(jwtUser, JacksonUtils.BeanToJson(roles));
        // 返回创建成功的token
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String tokenStr = JwtTokenUtils.TOKEN_PREFIX + token;
        log.info("token:{}", tokenStr);
        response.setHeader(JwtTokenUtils.TOKEN_HEADER, tokenStr);

    }

    /**
     * 这是验证失败时候调用的方法
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("验证失败, 理由: " + failed.getMessage());
    }

}
