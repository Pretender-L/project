package com.project.user.service;

import com.project.user.pojo.User;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface UserService {
    /***
     * 查询所有
     */
    List<User> findAll();

    /***
     * 根据ID查询
     */
    User findById(String username);

    /***
     * 新增
     */
    void add(User user);

    /***
     * 修改
     */
    void update(User user);

    /***
     * 删除
     */
    void delete(String username);

    /***
     * 多条件搜索
     */
    List<User> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     */
    Page<User> findPage(int page, int size);

    /***
     * 多条件分页查询
     */
    Page<User> findPage(Map<String, Object> searchMap, int page, int size);
}
