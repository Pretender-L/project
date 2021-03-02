package com.project.oauth.controller;

import com.project.common.entity.PageResult;
import com.project.common.entity.Result;
import com.project.oauth.pojo.Role;
import com.project.oauth.service.RoleService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /***
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll() {
        List<Role> roleList = roleService.findAll();
        return Result.success("查询成功", roleList);
    }

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        Role role = roleService.findById(id);
        return Result.success("查询成功", role);
    }

    /***
     * 新增数据
     * @param role
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Role role) {
        roleService.add(role);
        return Result.success("添加成功");
    }

    /***
     * 修改数据
     * @param role
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Role role, @PathVariable Integer id) {
        role.setId(id);
        roleService.update(role);
        return Result.success("修改成功");
    }

    /***
     * 根据ID删除数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        roleService.delete(id);
        return Result.success("删除成功");
    }

    /***
     * 多条件搜索数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap) {
        List<Role> list = roleService.findList(searchMap);
        return Result.success("查询成功", list);
    }

    /***
     * 分页搜索实现
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Role> pageList = roleService.findPage(searchMap, page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return Result.success("查询成功", pageResult);
    }

    /***
     * 根据adminId查询角色
     * @param adminId
     * @return
     */
    @GetMapping(value = "/findByAdminId/{adminId}")
    public Result<List<Role>> findByAdminId(@PathVariable Integer adminId) {
        List<Role> roleList = roleService.findByAdminId(adminId);
        return Result.success("查询成功", roleList);
    }
}
