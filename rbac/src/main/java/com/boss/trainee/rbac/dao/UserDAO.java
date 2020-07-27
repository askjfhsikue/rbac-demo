package com.boss.trainee.rbac.dao;

import com.boss.trainee.rbac.po.User;
import com.boss.trainee.rbac.service.dto.UserPermissionDTO;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/22 16:32
 */
@Repository
public interface UserDAO extends Mapper<User> {


    /**
     * 获取已启用的用户、角色、权限信息
     *
     * @param username
     * @return
     */
    @Select("select uid,username,password from user where username=#{username} and status=1")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "uid", property = "uid"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(property = "roleList", column = "uid",
                    many = @Many(select = "com.boss.trainee.rbac.dao.RoleDAO.getRoles"))
    })
    User get(String username);

    @Select("SELECT u.uid,p.id\n" +
            "FROM USER u,user_role ur,role r,role_permission rp,permission p\n" +
            "WHERE u.uid=ur.uid AND ur.role_id=r.id AND r.id=rp.role_id AND rp.permission_id=p.id AND u.uid=#{adminId} AND p.id=#{permissionId}")
    List<UserPermissionDTO> getUserPermissionDTO(@Param("adminId") Long adminId, @Param("permissionId") Long permissionId);


}
