package com.boss.trainee.rbac.utils;

import com.boss.trainee.rbac.entity.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/22 17:12
 */
@Data
@Component
public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String SUBJECT = "congge";

    public static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7;

    public static final String APPSECRET_KEY = "congge_secret";

    private static final String ROLE_CLAIMS = "rol";

//    public static String generateJsonWebToken(Users user) {
//
//        if (user.getId() == null || user.getUserName() == null || user.getFaceImage() == null) {
//            return null;
//        }
//
//        Map<String,Object> map = new HashMap<>();
//        map.put(ROLE_CLAIMS, "rol");
//
//        String token = Jwts
//                .builder()
//                .setSubject(SUBJECT)
//                .setClaims(map)
//                .claim("id", user.getId())
//                .claim("name", user.getUserName())
//                .claim("img", user.getFaceImage())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
//                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
//        return token;
//    }

    /**
     * 生成token
     *
     * @param userDTO
     * @param role
     * @return
     */
    public static String createToken(UserDTO userDTO, String role) {

        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, role);
        String username = userDTO.getUsername();
        String uid = userDTO.getUid() + "";
        String token = Jwts
                .builder()
                .setSubject(username)
                .setClaims(map)
                .claim("username", username)
                .claim("uid", uid)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }

    public static Claims checkJWT(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }

    /**
     * 获取用户名
     *
     * @param request
     * @return
     */
    public static Long getUid(HttpServletRequest request) {
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        Long uid = Long.valueOf(claims.get("uid").toString());
        return uid;
    }

    /**
     * 获取用户角色
     *
     * @param token
     * @return
     */
    public static String getUserRole(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("rol").toString();
    }

    /**
     * 是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }


}
