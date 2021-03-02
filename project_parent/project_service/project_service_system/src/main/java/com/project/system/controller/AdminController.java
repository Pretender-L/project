package com.project.system.controller;

import com.project.common.entity.PageResult;
import com.project.common.entity.Result;
import com.project.system.pojo.Admin;
import com.project.system.service.AdminService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /***
     * 查询全部数据
     * @return
     */
    @PreAuthorize("hasRole('超级管理员')")
    @GetMapping
    public Result findAll() {
        List<Admin> adminList = adminService.findAll();
        return Result.success("查询成功", adminList);
    }

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('超级管理员')")
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        Admin admin = adminService.findById(id);
        return Result.success("查询成功", admin);
    }

    /***
     * 新增数据
     * @param admin
     * @return
     */
    @PreAuthorize("hasRole('超级管理员')")
    @PostMapping
    public Result add(@RequestBody Admin admin) {
        adminService.add(admin);
        return Result.success("添加成功");
    }

    /***
     * 修改数据
     * @param admin
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('超级管理员')")
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Admin admin, @PathVariable Integer id) {
        admin.setId(id);
        adminService.update(admin);
        return Result.success("修改成功");
    }

    /***
     * 根据ID删除数据
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('超级管理员')")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        adminService.delete(id);
        return Result.success("删除成功");
    }

    /***
     * 多条件搜索数据
     * @param searchMap
     * @return
     */
    @PreAuthorize("hasRole('超级管理员')")
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap) {
        List<Admin> list = adminService.findList(searchMap);
        return Result.success("查询成功", list);
    }

    /***
     * 分页搜索实现
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @PreAuthorize("hasRole('超级管理员')")
    @GetMapping(value = "/search/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Admin> pageList = adminService.findPage(searchMap, page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return Result.success("查询成功", pageResult);
    }

    /***
     * 根据登录名查admin
     * @param loginName
     * @return
     */
    @PostMapping(value = "/findByLoginName/{loginName}")
    public Result<Admin> findByLoginName(@PathVariable("loginName") String loginName) {
        Admin admin = adminService.findByLoginName(loginName);
        return Result.success("查询成功", admin);
    }
}
