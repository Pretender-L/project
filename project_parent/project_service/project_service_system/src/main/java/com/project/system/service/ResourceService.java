package com.project.system.service;

import com.project.system.pojo.Resource;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ResourceService {
    /***
     * 查询所有
     */
    List<Resource> findAll();

    /***
     * 根据ID查询
     */
    Resource findById(Integer id);

    /***
     * 新增
     */
    void add(Resource resource);

    /***
     * 修改
     */
    void update(Resource resource);

    /***
     * 删除
     */
    void delete(Integer id);

    /***
     * 多条件搜索
     */
    List<Resource> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     */
    Page<Resource> findPage(int page, int size);

    /***
     * 多条件分页查询
     */
    Page<Resource> findPage(Map<String, Object> searchMap, int page, int size);

    /***
     * 根据roleId查询资源权限
     */
    List<Resource> findByRoleId(Integer roleId);

    /***
     * 根据adminId查询资源权限
     */
    Set<Resource> findByAdminId(Integer adminId);
}
