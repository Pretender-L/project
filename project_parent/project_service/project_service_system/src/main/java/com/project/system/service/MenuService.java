package com.project.system.service;

import com.project.system.pojo.Menu;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface MenuService {
    /***
     * 查询所有
     */
    List<Menu> findAll();

    /***
     * 根据ID查询
     */
    Menu findById(String id);

    /***
     * 新增
     */
    void add(Menu menu);

    /***
     * 修改
     */
    void update(Menu menu);

    /***
     * 删除
     */
    void delete(String id);

    /***
     * 多条件搜索
     */
    List<Menu> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     */
    Page<Menu> findPage(int page, int size);

    /***
     * 多条件分页查询
     */
    Page<Menu> findPage(Map<String, Object> searchMap, int page, int size);
}
