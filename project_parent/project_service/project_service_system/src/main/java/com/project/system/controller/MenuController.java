package com.project.system.controller;

import com.project.common.entity.PageResult;
import com.project.common.entity.Result;
import com.project.system.pojo.Menu;
import com.project.system.service.MenuService;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    /***
     * 查询全部数据
     */
    @GetMapping
    public Result<List<Menu>> findAll() {
        List<Menu> menuList = menuService.findAll();
        return Result.success("查询成功", menuList);
    }

    /***
     * 根据ID查询数据
     */
    @GetMapping("/{id}")
    public Result<Menu> findById(@PathVariable String id) {
        Menu menu = menuService.findById(id);
        return Result.success("查询成功", menu);
    }

    /***
     * 新增数据
     */
    @PostMapping
    public Result<?> add(@RequestBody Menu menu) {
        menuService.add(menu);
        return Result.success("添加成功");
    }

    /***
     * 修改数据
     */
    @PutMapping(value = "/{id}")
    public Result<?> update(@RequestBody Menu menu, @PathVariable String id) {
        menu.setId(id);
        menuService.update(menu);
        return Result.success("修改成功");
    }

    /***
     * 根据ID删除数据
     */
    @DeleteMapping(value = "/{id}")
    public Result<?> delete(@PathVariable String id) {
        menuService.delete(id);
        return Result.success("删除成功");
    }

    /***
     * 多条件搜索数据
     */
    @GetMapping(value = "/search")
    public Result<List<Menu>> findList(@RequestParam Map<String,Object> searchMap) {
        List<Menu> list = menuService.findList(searchMap);
        return Result.success("查询成功", list);
    }

    /***
     * 分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageResult<Menu>> findPage(@RequestParam Map<String,Object> searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Menu> pageList = menuService.findPage(searchMap, page, size);
        PageResult<Menu> pageResult = new PageResult<>(pageList.getTotal(), pageList.getResult());
        return Result.success("查询成功", pageResult);
    }
}
