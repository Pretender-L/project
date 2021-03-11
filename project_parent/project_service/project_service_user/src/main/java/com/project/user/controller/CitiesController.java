package com.project.user.controller;

import com.github.pagehelper.Page;
import com.project.common.entity.PageResult;
import com.project.common.entity.Result;
import com.project.user.pojo.Cities;
import com.project.user.service.CitiesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/cities")
public class CitiesController {
    @Resource
    private CitiesService citiesService;

    /***
     * 查询全部数据
     */
    @GetMapping
    public Result<List<Cities>> findAll() {
        List<Cities> citiesList = citiesService.findAll();
        return Result.success("查询成功", citiesList);
    }

    /***
     * 根据ID查询数据
     */
    @GetMapping("/{cityid}")
    public Result<Cities> findById(@PathVariable String cityid) {
        Cities cities = citiesService.findById(cityid);
        return Result.success("查询成功", cities);
    }

    /***
     * 新增数据
     */
    @PostMapping
    public Result<?> add(@RequestBody Cities cities) {
        citiesService.add(cities);
        return Result.success("添加成功");
    }

    /***
     * 修改数据
     */
    @PutMapping(value = "/{cityid}")
    public Result<?> update(@RequestBody Cities cities, @PathVariable String cityid) {
        cities.setCityid(cityid);
        citiesService.update(cities);
        return Result.success("修改成功");
    }

    /***
     * 根据ID删除
     */
    @DeleteMapping(value = "/{cityid}")
    public Result<?> delete(@PathVariable String cityid) {
        citiesService.delete(cityid);
        return Result.success("删除成功");
    }

    /***
     * 多条件搜索
     */
    @GetMapping(value = "/search")
    public Result<List<Cities>> findList(@RequestParam Map<String,Object> searchMap) {
        List<Cities> list = citiesService.findList(searchMap);
        return Result.success("查询成功", list);
    }

    /***
     * 分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageResult<Cities>> findPage(@RequestParam Map<String,Object> searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Cities> pageList = citiesService.findPage(searchMap, page, size);
        PageResult<Cities> pageResult = new PageResult<>(pageList.getTotal(), pageList.getResult());
        return Result.success("查询成功", pageResult);
    }
}
