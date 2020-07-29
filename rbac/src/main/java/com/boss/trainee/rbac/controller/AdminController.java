package com.boss.trainee.rbac.controller;

import com.boss.trainee.rbac.entity.dto.PermissionDTO;
import com.boss.trainee.rbac.entity.vo.RoleEditVO;
import com.boss.trainee.rbac.service.AdminService;
import com.boss.trainee.rbac.service.PermissionService;
import com.boss.trainee.rbac.service.RoleService;
import com.boss.trainee.rbac.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 管理员控制层
 *
 * @author: Jianbinbing
 * @Date: 2020/7/27 16:08
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 给用户设置角色
     *
     * @param editVO
     * @return
     */
    @GetMapping("/setRole")
    public Object setRole(RoleEditVO editVO) {
        Long adminId = JwtTokenUtils.getUid(request);
        editVO.setAdminId(adminId);
        adminService.setRole(editVO);
        return true;
    }

    /**
     * 删除用户的某个角色
     *
     * @param editVO
     * @return
     */
    @GetMapping("/removeRole")
    public Object removeRole(RoleEditVO editVO) {
        Long adminId = JwtTokenUtils.getUid(request);
        editVO.setAdminId(adminId);
        adminService.removeRole(editVO);
        return true;
    }

    /**
     * 给某个角色增加权限
     *
     * @param editVO
     * @return
     */
    @GetMapping("/addPermission")
    public Object addPermission(RoleEditVO editVO) {
        Long adminId = JwtTokenUtils.getUid(request);
        editVO.setAdminId(adminId);
        roleService.addPermissions(editVO);
        return true;
    }

    /**
     * 删除某个角色的某个权限
     *
     * @param editVO
     * @return
     */
    @GetMapping("/deletePermission")
    public Object deletePermission(RoleEditVO editVO) {
        Long adminId = JwtTokenUtils.getUid(request);
        editVO.setAdminId(adminId);
        roleService.removePermission(editVO);
        return true;
    }

    @PostMapping("/editRole")
    public Object editRole(@RequestBody RoleEditVO editVO) {
        Long adminId = JwtTokenUtils.getUid(request);
        editVO.setAdminId(adminId);
        adminService.forbidRole(editVO);
        return true;
    }

    /**
     * 新增权限
     *
     * @param permissionDTO
     * @return
     */
    @PostMapping("/insert")
    public Object insert(@RequestBody PermissionDTO permissionDTO) {
        permissionService.insert(permissionDTO);
        return true;
    }

    /**
     * 编辑权限
     *
     * @param permissionDTO
     * @return
     */
    @PostMapping("/editPermission")
    public Object editPermission(@RequestBody PermissionDTO permissionDTO) {
        permissionService.edit(permissionDTO);
        return true;
    }

}
