package com.project.demo.jpa.controller;

import com.project.common.entity.PageInfo;
import com.project.common.entity.PageResult;
import com.project.common.entity.Result;
import com.project.common.excetion.BadException;
import com.project.demo.jpa.service.UserService;
import com.project.demo.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate<String,?> redisTemplate;

    /***
     * 单个添加
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    /***
     * 批量添加
     */
    @PostMapping("/addAll")
    public Result<?> addAll(@RequestBody List<User> userList) {
        userService.addAll(userList);
        return Result.success();
    }

    /***
     * 单个删除
     */
    @DeleteMapping("/del/{userId}")
    public Result<?> del(@PathVariable("userId") String userId) {
        userService.del(userId);
        return Result.success();
    }

    /***
     * 批量删除
     */
    @DeleteMapping("/delAll")
    public Result<?> delAll(@RequestBody String[] userIds) {
        userService.delAll(userIds);
        return Result.success();
    }

    /***
     * 删库跑路
     */
    @DeleteMapping("/delDB")
    public Result<?> delDB() {
        userService.delDB();
        return Result.success();
    }

    /***
     * 更新（jpa会更新所有字段，会将对象中null值覆盖到数据库）
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    /***
     * 查询全部(存入redis)
     */
    @GetMapping("/findAll")
    public Result<List<User>> findAll() throws BadException {
        if (redisTemplate.hasKey("findAll")) {
            //字符串key value
            List<User> userList = (List<User>) redisTemplate.opsForValue().get("findAll");
            redisTemplate.expire("findAll", 60, TimeUnit.SECONDS);
            return Result.success(userList);
        }
        List<User> userList = userService.findAll();
        return Result.success(userList);
    }

    /***
     * 查询单个
     */
    @GetMapping("/find/{id}")
    public Result<User> find(@PathVariable("id") String id) throws BadException {
        User user = userService.find(id);
        return Result.success(user);
    }

    /***
     * 批量查询
     */
    @GetMapping("/batchQuery")
    //接收数组需要加@RequestParam 前端参数：?ids=1,2,3
    public Result<List<User>> batchQuery(@RequestParam List<String> ids) throws BadException {
        List<User> list = userService.batchQuery(ids);
        return Result.success(list);
    }

    /***
     * 根据生日排序(升序)
     */
    @GetMapping("/findAllSortAsc")
    public Result<List<User>> findAllBySort() throws BadException {
        List<User> list = userService.findAllSortAsc();
        return Result.success(list);
    }

    /***
     * 根据生日分页排序(降序)
     */
    @GetMapping("/findAllPageSortDesc")
    public Result<PageResult<User>> findAllPageSortDesc(PageInfo pageInfo) throws BadException {
        Page<User> page = userService.findAllPageSortDesc(pageInfo);
        PageResult<User> pageResult = new PageResult<>((long) page.getTotalPages(), page.getContent());
        return Result.success(pageResult);
    }

    /***
     * 分页 多个条件模糊查询所有
     * @param user 查询条件
     */
    @GetMapping("/findListByConditions")
    public Result<PageResult<User>> findListByConditions(PageInfo pageInfo, User user) {
        Page<User> page = userService.findListByConditions(pageInfo, user);
        PageResult<User> pageResult = new PageResult<>((long) page.getTotalPages(), page.getContent());
        return Result.success(pageResult);
    }

    /***
     * 分页 多个条件查询所有 日期查询很方便(sex,生日的日期区间)
     */
    @GetMapping("/search")
    public Result<PageResult<User>> search(User user, PageInfo pageInfo) {
        Page<User> page = userService.search(user, pageInfo);
        PageResult<User> pageResult = new PageResult<>((long) page.getTotalPages(), page.getContent());
        return Result.success(pageResult);
    }

    /***
     * 分页 单个条件模糊查询所有（查询条件不确定，后端限制为昵称，性别）
     */
    @GetMapping("/findListByCondition/{condition}")
    public Result<PageResult<User>> findListByCondition(PageInfo pageInfo, @PathVariable("condition") String condition) {
        Page<User> page = userService.findListByCondition(pageInfo, condition);
        PageResult<User> pageResult = new PageResult<>((long) page.getTotalPages(), page.getContent());
        return Result.success(pageResult);
    }

    /***
     * 多个条件查询单个对象
     * 结果必须为单个对象，多个结果报错（查询参数必须是数据库唯一）
     * @param userInfo 查询条件
     */
    @GetMapping("/findOne")
    public Result<User> findUserCondition(User userInfo) throws BadException {
        User user = userService.findOne(userInfo);
        return Result.success(user);
    }

    /***
     * 查询部分字段
     */
    @GetMapping("/findPart")
    public Result<List<Map<String,String>>> findPartUser(String sex) {
        List<Map<String,String>> userList = userService.findPartUser(sex);
        return Result.success(userList);
    }
}
