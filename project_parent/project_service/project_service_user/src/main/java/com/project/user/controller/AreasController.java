package com.project.user.controller;

import com.github.pagehelper.Page;
import com.project.common.entity.PageResult;
import com.project.common.entity.Result;
import com.project.user.pojo.Areas;
import com.project.user.service.AreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/areas")
public class AreasController {
    @Resource
    private AreasService areasService;

    /***
     * 查询全部数据
     */
    @GetMapping
    public Result<List<Areas>> findAll() {
        List<Areas> areasList = areasService.findAll();
        return Result.success("查询成功", areasList);
    }

    /***
     * 根据ID查询数据
     */
    @GetMapping("/{areaid}")
    public Result<Areas> findById(@PathVariable String areaid) {
        Areas areas = areasService.findById(areaid);
        return Result.success("查询成功", areas);
    }

    /***
     * 新增数据
     */
    @PostMapping
    public Result<?> add(@RequestBody Areas areas) {
        areasService.add(areas);
        return Result.success("添加成功");
    }

    /***
     * 修改数据
     */
    @PutMapping(value = "/{areaid}")
    public Result<?> update(@RequestBody Areas areas, @PathVariable String areaid) {
        areas.setAreaid(areaid);
        areasService.update(areas);
        return Result.success("修改成功");
    }

    /***
     * 根据ID删除
     */
    @DeleteMapping(value = "/{areaid}")
    public Result<?> delete(@PathVariable String areaid) {
        areasService.delete(areaid);
        return Result.success("删除成功");
    }

    /***
     * 多条件搜索
     */
    @GetMapping(value = "/search")
    public Result<List<Areas>> findList(@RequestParam Map<String,Object> searchMap) {
        List<Areas> list = areasService.findList(searchMap);
        return Result.success("查询成功", list);
    }

    /***
     * 分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageResult<Areas>> findPage(@RequestParam Map<String,Object> searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Areas> pageList = areasService.findPage(searchMap, page, size);
        PageResult<Areas> pageResult = new PageResult<>(pageList.getTotal(), pageList.getResult());
        return Result.success("查询成功", pageResult);
    }
}
