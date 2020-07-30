package com.boss.trainee.rbac.service.impl;

import com.boss.trainee.rbac.dao.RoleDAO;
import com.boss.trainee.rbac.dao.UserDAO;
import com.boss.trainee.rbac.dao.UserRoleDAO;
import com.boss.trainee.rbac.entity.dto.RoleDTO;
import com.boss.trainee.rbac.entity.po.User;
import com.boss.trainee.rbac.entity.po.UserRole;
import com.boss.trainee.rbac.entity.vo.role.RoleEditVO;
import com.boss.trainee.rbac.entity.vo.role.RoleStatusVO;
import com.boss.trainee.rbac.service.AdminService;
import com.boss.trainee.rbac.utils.JwtTokenUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 16:05
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private Mapper mapper;
    @Autowired
    private HttpServletRequest request;

    /**
     * 获取用户信息
     *
     * @param uid
     * @return
     */
    private User selectUser(Long uid) {
        return userDAO.selectByPrimaryKey(uid);
    }

    /**
     * 获取所有角色
     *
     * @return
     */
    private Map<Long, Object> getAllRoleId() {
        List<UserRole> userRoles = userRoleDAO.selectAll();
        Map<Long, Object> userRoleMap = new HashMap<>();
        for (UserRole userRole :
                userRoles) {
            userRoleMap.put(userRole.getRoleId(), userRole);
        }
        return userRoleMap;
    }

    /**
     * 校验是否存在该角色
     *
     * @param roleId
     * @return
     */
    private boolean checkRole(Long roleId) {
        Map<Long, Object> map = getAllRoleId();
        if (map.get(roleId) == null) {
            return false;
        }
        return true;
    }

    /**
     * 校验是否有权限进行操作
     *
     * @param roleDTO
     * @return
     */
    private boolean checkPermission(RoleDTO roleDTO) {
        Long uid = roleDTO.getUid();
        Long adminId = roleDTO.getAdminId();
        Long roleId = roleDTO.getRoleId();
        //判断用户是否存在
        if ((selectUser(uid) == null) || (selectUser(adminId) == null)) {
            return false;
        }
        //判断角色是否存在
        if (!checkRole(roleId)) {
            return false;
        }
        //判断授权人是否已有该角色,若无，则无法授权
        Map<Long, Long> roleMap = getRolesId(adminId);
        if (!roleMap.containsKey(roleId)) {
            return false;
        }
        return true;
    }

    @Override
    public Map<Long, Long> getRolesId(Long uid) {
        if (selectUser(uid) == null) {
            return null;
        }
        List<Long> rolesId = userRoleDAO.getRolesId(uid);
        Map<Long, Long> rs = new HashMap<>();
        for (Long id :
                rolesId) {
            rs.put(id, id);
        }
        return rs;
    }

    @Override
    public boolean setRole(RoleEditVO editVO) {
        Long adminId = JwtTokenUtils.getUid(request);
        RoleDTO roleDTO = mapper.map(editVO, RoleDTO.class);
        roleDTO.setAdminId(adminId);
        if (!checkPermission(roleDTO)) {
            return false;
        }
        //若有，则继续授权
        Long uid = editVO.getUid();
        Long roleId = editVO.getRoleId();
        //判断被授权人是否已有该角色,若有，则不授权
        Map<Long, Long> roleMap = getRolesId(uid);
        if (roleMap.containsKey(roleId)) {
            return true;
        }
        Date date = new Date();
        //若无，则继续授权
        UserRole userRole = mapper.map(editVO, UserRole.class);
        userRole.setCreateTime(date);
        userRoleDAO.insert(userRole);
        return true;
    }

    @Override
    public boolean removeRole(RoleEditVO editVO) {
        Long adminId = JwtTokenUtils.getUid(request);
        RoleDTO roleDTO = mapper.map(editVO, RoleDTO.class);
        roleDTO.setAdminId(adminId);
        if (!checkPermission(roleDTO)) {
            return false;
        }
        //若有，则继续删除
        Long uid = editVO.getUid();
        Long roleId = editVO.getRoleId();
        //判断被授权人是否已有该角色,若无，则不删除
        Map<Long, Long> roleMap = getRolesId(uid);
        if (!roleMap.containsKey(roleId)) {
            return false;
        }
        //若有，则继续删除
        UserRole userRole = mapper.map(editVO, UserRole.class);
        userRoleDAO.delete(userRole);

        return true;
    }

    @Override
    public boolean forbidRole(RoleStatusVO roleStatusVO) {
        Long adminId = JwtTokenUtils.getUid(request);
        RoleDTO roleDTO = mapper.map(roleStatusVO, RoleDTO.class);
        roleDTO.setAdminId(adminId);
        if (!checkPermission(roleDTO)) {
            return false;
        }
        Long roleId = roleStatusVO.getRoleId();
        Boolean status = roleStatusVO.getStatus();
        Date date = new Date();
        roleDAO.forbidRole(roleId, status, date);
        return false;
    }

}
