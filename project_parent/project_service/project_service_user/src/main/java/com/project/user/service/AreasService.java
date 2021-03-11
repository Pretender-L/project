package com.project.user.service;

import com.project.user.pojo.Areas;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface AreasService {
    /***
     * 查询所有
     */
    List<Areas> findAll();

    /***
     * 根据ID查询
     */
    Areas findById(String areaid);

    /***
     * 新增
     */
    void add(Areas areas);

    /***
     * 修改
     */
    void update(Areas areas);

    /***
     * 删除
     */
    void delete(String areaid);

    /***
     * 多条件搜索
     */
    List<Areas> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     */
    Page<Areas> findPage(int page, int size);

    /***
     * 多条件分页查询
     */
    Page<Areas> findPage(Map<String, Object> searchMap, int page, int size);
}
