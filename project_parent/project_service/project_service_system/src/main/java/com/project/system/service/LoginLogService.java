package com.project.system.service;


import com.project.system.pojo.LoginLog;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface LoginLogService {
    /***
     * 查询所有
     */
    List<LoginLog> findAll();

    /***
     * 根据ID查询
     */
    LoginLog findById(Integer id);

    /***
     * 新增
     */
    void add(LoginLog loginLog);

    /***
     * 修改
     */
    void update(LoginLog loginLog);

    /***
     * 删除
     */
    void delete(Integer id);

    /***
     * 多条件搜索
     */
    List<LoginLog> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     */
    Page<LoginLog> findPage(int page, int size);

    /***
     * 多条件分页查询
     */
    Page<LoginLog> findPage(Map<String, Object> searchMap, int page, int size);
}
