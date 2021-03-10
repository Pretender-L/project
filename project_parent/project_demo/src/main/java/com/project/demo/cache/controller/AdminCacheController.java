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
    public Result<?> findById(@PathVariable String adminId) {
        Admin admin = adminCacheService.findById(adminId);
        return Result.success(admin);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Admin admin){
        adminCacheService.update(admin);
        return Result.success();
    }
}
