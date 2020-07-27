package com.boss.trainee.rbac.dao;

import com.boss.trainee.rbac.po.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/22 16:33
 */
@Repository
public interface RoleDAO extends Mapper<Role> {


    /**
     * 获取已启用的角色信息及权限
     */
    @Select("select r.id,r.name from role r,user_role ur where r.id=ur.role_id and ur.uid=#{uid} and r.status=1")
    @Results(id = "roleMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(property = "permissionList", column = "id",
                    many = @Many(select = "com.boss.trainee.rbac.dao.PermissionDAO.getPermissions"))

    })
    List<Role> getRoles(Long uid);
}
