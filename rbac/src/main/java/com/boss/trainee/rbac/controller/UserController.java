package com.boss.trainee.rbac.controller;

import com.boss.trainee.rbac.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 检测权限控制
 *
 * @author: Jianbinbing
 * @Date: 2020/7/22 15:36
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/getRole")
    public Object test() {

        return roleService.count();
    }

    @GetMapping("/dev")
    public Object dev() {
        log.info("dev");
        return null;
    }

    @GetMapping("/master")
    public Object master() {
        log.info("master");
        return null;
    }

    @GetMapping("/admin")
    public Object admin() {
        log.info("admin");

        return null;
    }


}
