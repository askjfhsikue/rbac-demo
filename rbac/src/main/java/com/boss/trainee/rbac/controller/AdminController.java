package com.boss.trainee.rbac.controller;

import com.boss.trainee.rbac.service.AdminService;
import com.boss.trainee.rbac.service.RoleService;
import com.boss.trainee.rbac.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/setRole")
    public Object setRole(Long uid, Long roleId) {
        Long adminId = JwtTokenUtil.getUid(request);
        log.info("adminId:{}", adminId);
        adminService.setRole(adminId, uid, roleId);
        return true;
    }

    @GetMapping("/removeRole")
    public Object removeRole(Long uid, Long roleId) {
        Long adminId = JwtTokenUtil.getUid(request);
        log.info("adminId:{}", adminId);
        adminService.removeRole(adminId, uid, roleId);
        return true;
    }

    @GetMapping("/addPermission")
    public Object addPermission(Long roleId, Long permissionId) {
        Long adminId = JwtTokenUtil.getUid(request);
        roleService.addPermissions(adminId, roleId, permissionId);
        return true;
    }
}
