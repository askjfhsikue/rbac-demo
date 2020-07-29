package com.boss.trainee.rbac.dao;

import com.boss.trainee.rbac.entity.dto.RoleDTO;
import com.boss.trainee.rbac.entity.po.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
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


    /**
     * 分页获取所有角色
     *
     * @param start
     * @param length
     * @return
     */
    @Select("select id,name from role limit #{start},#{length}")
    List<RoleDTO> pageGet(@Param("start") Integer start, @Param("length") Integer length);

    /**
     * 获取指定用户的角色
     *
     * @param uid
     * @return
     */
    @Select("select r.id,r.name from role r,user_role ur where r.id=ur.role_id and ur.uid=#{uid}")
    List<RoleDTO> getUserRole(Long uid);

    /**
     * 修改角色状态角色
     *
     * @param roleId
     * @param status
     * @param updateTime
     * @return
     */
    @Select("update role set status=#{status},update_time=#{updateTime} where role_id=#{roleId}")
    boolean forbidRole(@Param("roleId") Long roleId, @Param("status") boolean status, @Param("updateTime") Date updateTime);
}
