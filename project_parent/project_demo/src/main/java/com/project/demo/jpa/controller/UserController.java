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
    private RedisTemplate<String,Object> redisTemplate;

    /***
     * 单个添加
     */
    @PostMapping("/add")
    public Result<Object> add(@RequestBody User user) {
        userService.add(user);
        return new Result<>().success();
    }

    /***
     * 批量添加
     */
    @PostMapping("/addAll")
    public Result<Object> addAll(@RequestBody List<User> userList) {
        userService.addAll(userList);
        return new Result<>().success();
    }

    /***
     * 单个删除
     */
    @DeleteMapping("/del/{userId}")
    public Result<Object> del(@PathVariable("userId") String userId) {
        userService.del(userId);
        return new Result<>().success();
    }

    /***
     * 批量删除
     * @param userIds
     * @return
     */
    @DeleteMapping("/delAll")
    public Result<Object> delAll(@RequestBody String[] userIds) {
        userService.delAll(userIds);
        return new Result<>().success();
    }

    /***
     * 删库跑路
     * @return
     */
    @DeleteMapping("/delDB")
    public Result<Object> delDB() {
        userService.delDB();
        return new Result<>().success();
    }

    /***
     * 更新（jpa会更新所有字段，会将对象中null值覆盖到数据库）
     * @param user
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@RequestBody User user) {
        userService.update(user);
        return new Result<>().success();
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
            return new Result<List<User>>().success(userList);
        }
        List<User> userList = userService.findAll();
        return new Result<List<User>>().success(userList);
    }

    /***
     * 查询单个
     */
    @GetMapping("/find/{id}")
    public Result<User> find(@PathVariable("id") String id) throws BadException {
        User user = userService.find(id);
        return new Result<User>().success(user);
    }

    /***
     * 批量查询
     * @param ids
     * @return
     */
    @GetMapping("/batchQuery")
    //接收数组需要加@RequestParam 前端参数：?ids=1,2,3
    public Result<List<User>> batchQuery(@RequestParam List<String> ids) throws BadException {
        List<User> list = userService.batchQuery(ids);
        return new Result<List<User>>().success(list);
    }

    /***
     * 根据生日排序(升序)
     */
    @GetMapping("/findAllSortAsc")
    public Result<List<User>> findAllBySort() throws BadException {
        List<User> list = userService.findAllSortAsc();
        return new Result<List<User>>().success(list);
    }

    /***
     * 根据生日分页排序(降序)
     * @return
     */
    @GetMapping("/findAllPageSortDesc/{page}/{size}")
    public Result<PageResult<List<User>>> findAllPageSortDesc(PageInfo pageInfo) throws BadException {
        Page<User> page = userService.findAllPageSortDesc(pageInfo);
        PageResult<Page<User>> pageResult = new PageResult<List<User>>((long) page.getTotalPages(), page.getContent());
        return new Result<PageResult<List<User>>>().success(pageResult);
    }

    /***
     * 分页 多个条件模糊查询所有
     * @param user 查询条件
     * @return
     */
    @GetMapping("/findListByConditions")
    public Result findListByConditions(PageInfo pageInfo, User user) {
        Page<User> page = userService.findListByConditions(pageInfo, user);
        PageResult<User> pageResult = new PageResult<>((long) page.getTotalPages(), page.getContent());
        return new Result().success(pageResult);
    }

    /***
     * 分页 多个条件查询所有 日期查询很方便(sex,生日的日期区间)
     * @param user
     * @param pageInfo
     * @return
     */
    @GetMapping("/search")
    public Result search(User user, PageInfo pageInfo) {
        Page page = userService.search(user, pageInfo);
        PageResult<User> pageResult = new PageResult<>((long) page.getTotalPages(), page.getContent());
        return new Result().success(pageResult);
    }

    /***
     * 分页 单个条件模糊查询所有（查询条件不确定，后端限制为昵称，性别）
     */
    @GetMapping("/findListByCondition/{condition}")
    public Result findListByCondition(PageInfo pageInfo, @PathVariable("condition") String condition) {
        Page<User> page = userService.findListByCondition(pageInfo, condition);
        PageResult<User> pageResult = new PageResult<>((long) page.getTotalPages(), page.getContent());
        return new Result().success(pageResult);
    }

    /***
     * 多个条件查询单个对象
     * 结果必须为单个对象，多个结果报错（查询参数必须是数据库唯一）
     * @param userInfo 查询条件
     * @return
     */
    @GetMapping("/findOne")
    public Result findUserCondition(User userInfo) throws BadException {
        User user = userService.findOne(userInfo);
        return new Result().success(user);
    }

    /***
     * 查询部分字段
     */
    @GetMapping("/findPart")
    public Result findPartUser(String sex) {
        List<Map> userList = userService.findPartUser(sex);
        return new Result().success(userList);
    }
}
