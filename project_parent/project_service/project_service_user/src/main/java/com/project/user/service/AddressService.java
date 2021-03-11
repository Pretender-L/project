package com.project.user.service;

import com.project.user.pojo.Address;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface AddressService {
    /***
     * 查询所有
     */
    List<Address> findAll();

    /***
     * 根据ID查询
     */
    Address findById(Integer id);

    /***
     * 新增
     */
    void add(Address address);

    /***
     * 修改
     */
    void update(Address address);

    /***
     * 删除
     */
    void delete(Integer id);

    /***
     * 多条件搜索
     */
    List<Address> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     */
    Page<Address> findPage(int page, int size);

    /***
     * 多条件分页查询
     */
    Page<Address> findPage(Map<String, Object> searchMap, int page, int size);

    /***
     * 根据登陆人名称获取相关收获人地址
     */
    List<Address> list(String username);
}
