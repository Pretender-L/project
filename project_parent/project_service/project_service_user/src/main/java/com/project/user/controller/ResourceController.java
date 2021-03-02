package com.project.user.controller;

import com.github.pagehelper.Page;
import com.project.common.entity.PageResult;
import com.project.common.entity.Result;
import com.project.user.pojo.Resource;
import com.project.user.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    /***
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll() {
        List<Resource> resourceList = resourceService.findAll();
        return Result.success("查询成功", resourceList);
    }

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        Resource resource = resourceService.findById(id);
        return Result.success("查询成功", resource);
    }

    /***
     * 新增数据
     * @param resource
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Resource resource) {
        resourceService.add(resource);
        return Result.success("添加成功");
    }

    /***
     * 修改数据
     * @param resource
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Resource resource, @PathVariable Integer id) {
        resource.setId(id);
        resourceService.update(resource);
        return Result.success("修改成功");
    }

    /***
     * 根据ID删除数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        resourceService.delete(id);
        return Result.success("删除成功");
    }

    /***
     * 多条件搜索数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap) {
        List<Resource> list = resourceService.findList(searchMap);
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
        Page<Resource> pageList = resourceService.findPage(searchMap, page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return Result.success("查询成功", pageResult);
    }

    /***
     * 根据username查询资源权限
     * @param username
     * @return
     */
    @GetMapping(value = "/findByUsername/{username}")
    public Result<List<Resource>> findByUsername(@PathVariable String username) {
        List<Resource> resourceList = resourceService.findByUsername(username);
        return Result.success("查询成功", resourceList);
    }
}
