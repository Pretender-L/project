package com.project.demo.cache.controller;

import com.project.demo.cache.service.AdminCacheService;
import com.project.common.entity.Result;
import com.project.demo.pojo.Admin;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/adminCache")
public class AdminCacheController {
    @Resource
    private AdminCacheService adminCacheService;

    @GetMapping("/findById/{adminId}")
    public Result<Admin> findById(@PathVariable String adminId) {
        Admin admin = adminCacheService.findById(adminId);
        return new Result<Admin>().success(admin);
    }

    @PutMapping("/update")
    public Result<Admin> update(@RequestBody Admin admin) {
        adminCacheService.update(admin);
        return new Result<Admin>().success();
    }
}
