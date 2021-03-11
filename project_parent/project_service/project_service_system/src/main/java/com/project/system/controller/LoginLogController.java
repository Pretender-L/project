package com.project.system.controller;

import com.project.common.entity.PageResult;
import com.project.common.entity.Result;
import com.project.system.pojo.LoginLog;
import com.project.system.service.LoginLogService;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/loginLog")
public class LoginLogController {
    @Resource
    private LoginLogService loginLogService;

    /***
     * 查询全部数据
     */
    @GetMapping
    public Result<List<LoginLog>> findAll() {
        List<LoginLog> loginLogList = loginLogService.findAll();
        return Result.success("查询成功", loginLogList);
    }

    /***
     * 根据ID查询数据
     */
    @GetMapping("/{id}")
    public Result<LoginLog> findById(@PathVariable Integer id) {
        LoginLog loginLog = loginLogService.findById(id);
        return Result.success("查询成功", loginLog);
    }

    /***
     * 新增数据
     */
    @PostMapping
    public Result<?> add(@RequestBody LoginLog loginLog) {
        loginLogService.add(loginLog);
        return Result.success("添加成功");
    }

    /***
     * 修改数据
     */
    @PutMapping(value = "/{id}")
    public Result<?> update(@RequestBody LoginLog loginLog, @PathVariable Integer id) {
        loginLog.setId(id);
        loginLogService.update(loginLog);
        return Result.success("修改成功");
    }

    /***
     * 根据ID删除数据
     */
    @DeleteMapping(value = "/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        loginLogService.delete(id);
        return Result.success("删除成功");
    }

    /***
     * 多条件搜索数据
     */
    @GetMapping(value = "/search")
    public Result<List<LoginLog>> findList(@RequestParam Map<String,Object> searchMap) {
        List<LoginLog> list = loginLogService.findList(searchMap);
        return Result.success("查询成功", list);
    }

    /***
     * 分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageResult<LoginLog>> findPage(@RequestParam Map<String,Object> searchMap, @PathVariable int page, @PathVariable int size) {
        Page<LoginLog> pageList = loginLogService.findPage(searchMap, page, size);
        PageResult<LoginLog> pageResult = new PageResult<>(pageList.getTotal(), pageList.getResult());
        return Result.success("查询成功", pageResult);
    }
}
