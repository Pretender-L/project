package com.project.system.controller;

import com.project.common.entity.PageResult;
import com.project.common.entity.Result;
import com.project.system.pojo.Admin;
import com.project.system.service.AdminService;
import com.github.pagehelper.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    /***
     * 查询全部数据
     */
    @PreAuthorize("hasRole('超级管理员')")
    @GetMapping
    public Result<?> findAll() {
        List<Admin> adminList = adminService.findAll();
        return Result.success("查询成功", adminList);
    }

    /***
     * 根据ID查询数据
     */
    @PreAuthorize("hasRole('超级管理员')")
    @GetMapping("/{id}")
    public Result<Admin> findById(@PathVariable Integer id) {
        Admin admin = adminService.findById(id);
        return Result.success("查询成功", admin);
    }

    /***
     * 新增数据
     */
    @PreAuthorize("hasRole('超级管理员')")
    @PostMapping
    public Result<?> add(@RequestBody Admin admin) {
        adminService.add(admin);
        return Result.success("添加成功");
    }

    /***
     * 修改数据
     */
    @PreAuthorize("hasRole('超级管理员')")
    @PutMapping(value = "/{id}")
    public Result<?> update(@RequestBody Admin admin, @PathVariable Integer id) {
        admin.setId(id);
        adminService.update(admin);
        return Result.success("修改成功");
    }

    /***
     * 根据ID删除数据
     */
    @PreAuthorize("hasRole('超级管理员')")
    @DeleteMapping(value = "/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        adminService.delete(id);
        return Result.success("删除成功");
    }

    /***
     * 多条件搜索数据
     */
    @PreAuthorize("hasRole('超级管理员')")
    @GetMapping(value = "/search")
    public Result<List<Admin>> findList(@RequestParam Map<String, Object> searchMap) {
        List<Admin> list = adminService.findList(searchMap);
        return Result.success("查询成功", list);
    }

    /***
     * 分页搜索实现
     */
    @PreAuthorize("hasRole('超级管理员')")
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageResult<Admin>> findPage(@RequestParam Map<String, Object> searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Admin> pageList = adminService.findPage(searchMap, page, size);
        PageResult<Admin> pageResult = new PageResult<>(pageList.getTotal(), pageList.getResult());
        return Result.success("查询成功", pageResult);
    }

    /***
     * 根据登录名查admin
     */
    @PostMapping(value = "/findByLoginName/{loginName}")
    public Result<Admin> findByLoginName(@PathVariable("loginName") String loginName) {
        Admin admin = adminService.findByLoginName(loginName);
        return Result.success("查询成功", admin);
    }
}
