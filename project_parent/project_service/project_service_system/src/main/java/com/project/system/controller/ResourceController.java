package com.project.system.controller;

import com.project.common.entity.PageResult;
import com.project.common.entity.Result;
import com.project.system.pojo.Resource;
import com.project.system.service.ResourceService;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/resource")
public class ResourceController {
    @javax.annotation.Resource
    private ResourceService resourceService;

    /***
     * 查询全部数据
     */
    @GetMapping
    public Result<List<Resource>> findAll() {
        List<Resource> resourceList = resourceService.findAll();
        return Result.success("查询成功", resourceList);
    }

    /***
     * 根据ID查询数据
     */
    @GetMapping("/{id}")
    public Result<Resource> findById(@PathVariable Integer id) {
        Resource resource = resourceService.findById(id);
        return Result.success("查询成功", resource);
    }

    /***
     * 新增数据
     */
    @PostMapping
    public Result<?> add(@RequestBody Resource resource) {
        resourceService.add(resource);
        return Result.success("添加成功");
    }

    /***
     * 修改数据
     */
    @PutMapping(value = "/{id}")
    public Result<?> update(@RequestBody Resource resource, @PathVariable Integer id) {
        resource.setId(id);
        resourceService.update(resource);
        return Result.success("修改成功");
    }

    /***
     * 根据ID删除数据
     */
    @DeleteMapping(value = "/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        resourceService.delete(id);
        return Result.success("删除成功");
    }

    /***
     * 多条件搜索数据
     */
    @GetMapping(value = "/search")
    public Result<List<Resource>> findList(@RequestParam Map<String,Object> searchMap) {
        List<Resource> list = resourceService.findList(searchMap);
        return Result.success("查询成功", list);
    }

    /***
     * 分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageResult<Resource>> findPage(@RequestParam Map<String, Object> searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Resource> pageList = resourceService.findPage(searchMap, page, size);
        PageResult<Resource> pageResult = new PageResult<>(pageList.getTotal(), pageList.getResult());
        return Result.success("查询成功", pageResult);
    }

    /***
     * 根据roleId查询资源权限
     */

    @GetMapping(value = "/findByRoleId/{roleId}")
    public Result<List<Resource>> findByRoleId(@PathVariable Integer roleId) {
        List<Resource> resourceList = resourceService.findByRoleId(roleId);
        return Result.success("查询成功", resourceList);
    }

    /***
     * 根据adminId查询资源权限
     */
    @GetMapping(value = "/findByAdminId/{adminId}")
    public Result<Set<Resource>> findByAdminId(@PathVariable Integer adminId) {
        Set<Resource> resourceSet = resourceService.findByAdminId(adminId);
        return Result.success("查询成功", resourceSet);
    }
}
