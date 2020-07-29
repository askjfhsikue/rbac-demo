package com.boss.trainee.rbac.dao;

import com.boss.trainee.rbac.entity.po.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/22 16:34
 */
@Repository
public interface RolePermissionDAO extends Mapper<RolePermission> {

    /**
     * 根据角色和权限获取id
     *
     * @param roleId
     * @param permissionId
     * @return
     */
    @Select("select id from role_permission where role_id=#{roleId} and permission_id=#{permissionId}")
    Long get(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);


}
