package com.project.user.service;

import com.project.user.pojo.Provinces;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface ProvincesService {
    /***
     * 查询所有
     */
    List<Provinces> findAll();

    /***
     * 根据ID查询
     */
    Provinces findById(String provinceid);

    /***
     * 新增
     */
    void add(Provinces provinces);

    /***
     * 修改
     */
    void update(Provinces provinces);

    /***
     * 删除
     */
    void delete(String provinceid);

    /***
     * 多条件搜索
     */
    List<Provinces> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     */
    Page<Provinces> findPage(int page, int size);

    /***
     * 多条件分页查询
     */
    Page<Provinces> findPage(Map<String, Object> searchMap, int page, int size);
}
