package com.project.system.service;

import com.project.system.pojo.Role;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface RoleService {
    /***
     * 查询所有
     */
    List<Role> findAll();

    /***
     * 根据ID查询
     */
    Role findById(Integer id);

    /***
     * 新增
     */
    void add(Role role);

    /***
     * 修改
     */
    void update(Role role);

    /***
     * 删除
     */
    void delete(Integer id);

    /***
     * 多条件搜索
     */
    List<Role> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     */
    Page<Role> findPage(int page, int size);

    /***
     * 多条件分页查询
     */
    Page<Role> findPage(Map<String, Object> searchMap, int page, int size);

    /***
     * 根据adminId查询资源权限
     */
    List<Role> findByAdminId(Integer adminId);
}
