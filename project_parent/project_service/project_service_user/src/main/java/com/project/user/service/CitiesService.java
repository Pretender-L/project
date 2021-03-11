package com.project.user.service;

import com.project.user.pojo.Cities;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface CitiesService {
    /***
     * 查询所有
     */
    List<Cities> findAll();

    /***
     * 根据ID查询
     */
    Cities findById(String cityid);

    /***
     * 新增
     */
    void add(Cities cities);

    /***
     * 修改
     */
    void update(Cities cities);

    /***
     * 删除
     */
    void delete(String cityid);

    /***
     * 多条件搜索
     */
    List<Cities> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     */
    Page<Cities> findPage(int page, int size);

    /***
     * 多条件分页查询
     */
    Page<Cities> findPage(Map<String, Object> searchMap, int page, int size);
}
