package com.boss.trainee.rbac.controller;

import com.boss.trainee.rbac.entity.vo.permission.PermissionVO;
import com.boss.trainee.rbac.entity.vo.roleVO.RoleEditVO;
import com.boss.trainee.rbac.entity.vo.roleVO.RolePermissionEditVO;
import com.boss.trainee.rbac.entity.vo.roleVO.RoleStatusVO;
import com.boss.trainee.rbac.service.AdminService;
import com.boss.trainee.rbac.service.PermissionService;
import com.boss.trainee.rbac.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/27 16:08
 */
@Api(tags = "权限管理接口")
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


    @ApiOperation(value = "为用户设置角色")
    @GetMapping("/setRole")
    public Object setRole(@ApiParam(value = "新增角色的参数", required = true) RoleEditVO editVO) {
        adminService.setRole(editVO);
        return true;
    }


    @ApiOperation(value = "删除用户的某个角色")
    @GetMapping("/removeRole")
    public Object removeRole(@ApiParam(value = "删除角色的参数", required = true) RoleEditVO editVO) {
        adminService.removeRole(editVO);
        return true;
    }


    @ApiOperation(value = "给指定角色增加权限")
    @GetMapping("/addPermission")
    public Object addPermission(@ApiParam(value = "新增权限的参数", required = true) RolePermissionEditVO rolePermissionEditVO) {
        roleService.addPermissions(rolePermissionEditVO);
        return true;
    }


    @ApiOperation(value = "删除指定角色的指定权限")
    @GetMapping("/deletePermission")
    public Object deletePermission(@ApiParam(value = "删除权限的参数", required = true) RolePermissionEditVO rolePermissionEditVO) {
        roleService.removePermission(rolePermissionEditVO);
        return true;
    }

    @ApiOperation(value = "编辑角色状态", notes = "禁用或者启用")
    @PostMapping("/editRole")
    public Object editRole(@ApiParam(value = "编辑角色的参数", required = true) @RequestBody RoleStatusVO editVO) {

        adminService.forbidRole(editVO);
        return true;
    }

    @ApiOperation(value = "新增权限")
    @PostMapping("/insert")
    public Object insert(@ApiParam(value = "新增权限的参数", required = true) @RequestBody PermissionVO permissionVO) {
        permissionService.insert(permissionVO);
        return true;
    }


    @ApiOperation(value = "编辑权限")
    @PostMapping("/editPermission")
    public Object editPermission(@RequestBody PermissionVO permissionVO) {
        permissionService.edit(permissionVO);
        return true;
    }

    @ApiOperation(value = "查询指定用户的角色")
    @GetMapping("/getRoles")
    public Object getRoles() {

        return roleService.getUserRole();
    }


}
