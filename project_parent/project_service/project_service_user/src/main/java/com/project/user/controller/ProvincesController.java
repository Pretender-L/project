package com.project.user.controller;

import com.github.pagehelper.Page;
import com.project.common.entity.PageResult;
import com.project.common.entity.Result;
import com.project.user.pojo.Provinces;
import com.project.user.service.ProvincesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/provinces")
public class ProvincesController {
    @Resource
    private ProvincesService provincesService;

    /***
     * 查询全部数据
     */
    @GetMapping
    public Result<List<Provinces>> findAll() {
        List<Provinces> provincesList = provincesService.findAll();
        return Result.success("查询成功", provincesList);
    }

    /***
     * 根据ID查询数据
     */
    @GetMapping("/{provinceId}")
    public Result<Provinces> findById(@PathVariable String provinceId) {
        Provinces provinces = provincesService.findById(provinceId);
        return Result.success("查询成功", provinces);
    }

    /***
     * 新增数据
     */
    @PostMapping
    public Result<?> add(@RequestBody Provinces provinces) {
        provincesService.add(provinces);
        return Result.success("添加成功");
    }

    /***
     * 修改数据
     */
    @PutMapping(value = "/{provinceid}")
    public Result<?> update(@RequestBody Provinces provinces, @PathVariable String provinceid) {
        provinces.setProvinceid(provinceid);
        provincesService.update(provinces);
        return Result.success("修改成功");
    }

    /***
     * 根据ID删除
     */
    @DeleteMapping(value = "/{provinceid}")
    public Result<?> delete(@PathVariable String provinceid) {
        provincesService.delete(provinceid);
        return Result.success("删除成功");
    }

    /***
     * 多条件搜索
     */
    @GetMapping(value = "/search")
    public Result<List<Provinces>> findList(@RequestParam Map<String,Object> searchMap) {
        List<Provinces> list = provincesService.findList(searchMap);
        return Result.success("查询成功", list);
    }

    /***
     * 分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageResult<Provinces>> findPage(@RequestParam Map<String,Object> searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Provinces> pageList = provincesService.findPage(searchMap, page, size);
        PageResult<Provinces> pageResult = new PageResult<>(pageList.getTotal(), pageList.getResult());
        return Result.success("查询成功", pageResult);
    }
}
