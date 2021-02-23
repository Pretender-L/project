package com.project.cache.controller;

import com.project.cache.service.AdminCacheService;
import com.project.pojo.Admin;
import com.project.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminCache")
public class AdminCacheController {
    @Autowired
    private AdminCacheService adminCacheService;


    @GetMapping("/findById/{adminId}")
    public Result findById(@PathVariable String adminId) {
        Admin admin = adminCacheService.findById(adminId);
        return Result.success(admin);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Admin admin){
        adminCacheService.update(admin);
        return Result.success();
    }
}
