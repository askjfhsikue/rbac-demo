package com.boss.trainee.rbac.controller;

import com.boss.trainee.rbac.service.AdminService;
import com.boss.trainee.rbac.service.PermissionService;
import com.boss.trainee.rbac.service.RoleService;
import com.boss.trainee.rbac.service.dto.PermissionDTO;
import com.boss.trainee.rbac.utils.JwtTokenUtil;
import com.boss.trainee.rbac.vo.RoleEditVO;
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

    @GetMapping("/setRole")
    public Object setRole(RoleEditVO editVO) {
        Long adminId = JwtTokenUtil.getUid(request);
        editVO.setAdminId(adminId);
        adminService.setRole(editVO);
        return true;
    }

    @GetMapping("/removeRole")
    public Object removeRole(RoleEditVO editVO) {
        Long adminId = JwtTokenUtil.getUid(request);
        editVO.setAdminId(adminId);
        adminService.removeRole(editVO);
        return true;
    }

    @GetMapping("/addPermission")
    public Object addPermission(RoleEditVO editVO) {
        Long adminId = JwtTokenUtil.getUid(request);
        editVO.setAdminId(adminId);
        roleService.addPermissions(editVO);
        return true;
    }

    @GetMapping("/deletePermission")
    public Object deletePermission(RoleEditVO editVO) {
        Long adminId = JwtTokenUtil.getUid(request);
        editVO.setAdminId(adminId);
        roleService.removePermission(editVO);
        return true;
    }

    @PostMapping("/insert")
    public Object insert(@RequestBody PermissionDTO permissionDTO) {
        permissionService.insert(permissionDTO);
        return true;
    }
}
