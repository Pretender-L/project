package com.project.user.service;

import com.github.pagehelper.Page;
import com.project.user.pojo.Resource;

import java.util.List;
import java.util.Map;

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
     * 根据userId查询资源权限
     */
    List<Resource> findByUsername(String username);
}
