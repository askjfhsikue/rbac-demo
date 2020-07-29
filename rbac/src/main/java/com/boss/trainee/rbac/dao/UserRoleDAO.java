package com.boss.trainee.rbac.dao;

import com.boss.trainee.rbac.entity.po.UserRole;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/22 16:33
 */
@Repository
public interface UserRoleDAO extends Mapper<UserRole> {


    /**
     * 获取已启用的角色id
     *
     * @param uid
     * @return
     */
    @Select("SELECT ur.role_id FROM user_role ur INNER JOIN role r ON role_id=r.id AND r.status=1 AND ur.uid=#{uid}")
    List<Long> getRolesId(Long uid);


}
