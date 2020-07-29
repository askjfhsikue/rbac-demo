package com.boss.trainee.rbac.dao;

import com.boss.trainee.rbac.entity.dto.PermissionDTO;
import com.boss.trainee.rbac.entity.dto.RolePermissionDTO;
import com.boss.trainee.rbac.entity.po.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/22 16:34
 */
@Repository
public interface PermissionDAO extends Mapper<Permission>, InsertListMapper<Permission> {


    /**
     * 获取某个角色的所有权限
     *
     * @param id
     * @return
     */
    @Select("select p.id,p.title,p.url from permission p,role_permission rp " +
            "where p.id=rp.permission_id and role_id=#{id}")
    List<Permission> getPermissions(Long id);

    /**
     * 获取已启用的角色、权限信息
     *
     * @return
     */
    @Select("select r.name,p.url from role r,role_permission rp,permission p " +
            "where r.id=rp.role_id and rp.permission_id=p.id and r.status=1 and p.status=1")
    List<RolePermissionDTO> getRolePermissionDTO();

    /**
     * 分页获取指定角色的所有权限
     *
     * @param roleId
     * @param start
     * @param length
     * @return
     */
    @Select("select p.id,p.title,p.url,p.status from permission p,role_permission rp " +
            "where p.id=rp.permission_id and role_id=#{id} limit #{start},#{length}")
    List<PermissionDTO> pageGetRolePermission(@Param("roleId") Long roleId, @Param("start") Integer start
            , @Param("length") Integer length);


}
