package com.project.system.service;

import com.project.system.pojo.Admin;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface AdminService {
    /***
     * 查询所有
     */
    List<Admin> findAll();

    /***
     * 根据ID查询
     */
    Admin findById(Integer id);

    /***
     * 新增
     */
    void add(Admin admin);

    /***
     * 修改
     */
    void update(Admin admin);

    /***
     * 删除
     */
    void delete(Integer id);

    /***
     * 多条件搜索
     */
    List<Admin> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     */
    Page<Admin> findPage(int page, int size);

    /***
     * 多条件分页查询
     */
    Page<Admin> findPage(Map<String, Object> searchMap, int page, int size);

    /***
     * 根据登录名查询admin
     */
    Admin findByLoginName(String loginName);
}
