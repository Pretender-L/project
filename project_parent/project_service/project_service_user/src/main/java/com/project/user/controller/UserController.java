package com.project.user.controller;

import com.github.pagehelper.Page;
import com.project.common.entity.PageResult;
import com.project.common.entity.Result;
import com.project.user.pojo.User;
import com.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /***
     * 查询全部数据
     * @return
     */
    @PreAuthorize("hasAuthority('user')")
    @GetMapping
    public Result findAll() {
        List<User> userList = userService.findAll();
        return Result.success("查询成功", userList);
    }

    /***
     * 根据ID查询数据
     * @param username
     * @return
     */
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/{username}")
    public Result findById(@PathVariable String username) {
        User user = userService.findById(username);
        return Result.success("查询成功", user);
    }

    /***
     * 登录查找用户信息
     * @param username
     * @return
     */
    @GetMapping("/load/{username}")
    public Result<User> findUserInfo(@PathVariable("username") String username) {
        User user = userService.findById(username);
        return Result.success("查询成功", user);
    }

    /***
     * 新增数据
     * @param user
     * @return
     */
    @PreAuthorize("hasAuthority('user')")
    @PostMapping
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success("添加成功");
    }

    /***
     * 修改数据
     * @param user
     * @param username
     * @return
     */
    @PreAuthorize("hasAuthority('user')")
    @PutMapping(value = "/{username}")
    public Result update(@RequestBody User user, @PathVariable String username) {
        user.setUsername(username);
        userService.update(user);
        return Result.success("修改成功");
    }

    /***
     * 根据ID删除
     * @param username
     * @return
     */
    @PreAuthorize("hasAuthority('user')")
    @DeleteMapping(value = "/{username}")
    public Result delete(@PathVariable String username) {
        userService.delete(username);
        return Result.success("删除成功");
    }

    /***
     * 多条件搜索
     * @param searchMap
     * @return
     */
    @PreAuthorize("hasAuthority('user')")
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap) {
        List<User> list = userService.findList(searchMap);
        return Result.success("查询成功", list);
    }

    /***
     * 分页搜索实现
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @PreAuthorize("hasAuthority('user')")
    @GetMapping(value = "/search/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<User> pageList = userService.findPage(searchMap, page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return Result.success("查询成功", pageResult);
    }
}
