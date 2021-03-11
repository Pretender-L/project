package com.project.system.service.impl;

import com.project.system.pojo.Resource;
import com.project.system.pojo.Role;
import com.project.system.service.ResourceService;
import com.project.system.dao.ResourceMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.project.system.service.RoleService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class ResourceServiceImpl implements ResourceService {
    @javax.annotation.Resource
    private ResourceMapper resourceMapper;
    @javax.annotation.Resource
    private RoleService roleService;

    /***
     * 查询全部列表
     */
    @Override
    public List<Resource> findAll() {
        return resourceMapper.selectAll();
    }

    /***
     * 根据ID查询
     */
    @Override
    public Resource findById(Integer id) {
        return resourceMapper.selectByPrimaryKey(id);
    }

    /***
     * 增加
     */
    @Override
    public void add(Resource resource) {
        resourceMapper.insert(resource);
    }

    /***
     * 修改
     */
    @Override
    public void update(Resource resource) {
        resourceMapper.updateByPrimaryKey(resource);
    }

    /***
     * 删除
     */
    @Override
    public void delete(Integer id) {
        resourceMapper.deleteByPrimaryKey(id);
    }

    /***
     * 条件查询
     */
    @Override
    public List<Resource> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return resourceMapper.selectByExample(example);
    }

    /***
     * 分页查询
     */
    @Override
    public Page<Resource> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        return (Page<Resource>) resourceMapper.selectAll();
    }

    /***
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @Override
    public Page<Resource> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(searchMap);
        return (Page<Resource>) resourceMapper.selectByExample(example);
    }

    /***
     * 构建查询对象
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Resource.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // res_key
            if (searchMap.get("res_key") != null && !"".equals(searchMap.get("res_key"))) {
                criteria.andLike("res_key", "%" + searchMap.get("res_key") + "%");
            }
            // res_name
            if (searchMap.get("res_name") != null && !"".equals(searchMap.get("res_name"))) {
                criteria.andLike("res_name", "%" + searchMap.get("res_name") + "%");
            }
            // id
            if (searchMap.get("id") != null) {
                criteria.andEqualTo("id", searchMap.get("id"));
            }
            // parent_id
            if (searchMap.get("parentId") != null) {
                criteria.andEqualTo("parentId", searchMap.get("parentId"));
            }
        }
        return example;
    }

    /***
     * 根据roleId查询资源权限
     */
    @Override
    public List<Resource> findByRoleId(Integer roleId) {
        return resourceMapper.findByRoleId(roleId);
    }

    /***
     * 根据adminId查询资源权限
     */
    @Override
    public Set<Resource> findByAdminId(Integer adminId) {
        List<Role> roleList = roleService.findByAdminId(adminId);
        //将每个resourceList整合到一个集合并去重（每个角色对应的资源可能会重复）
        Set<Resource> resourceSet = new HashSet<>();
        for (Role role : roleList) {
            List<Resource> list = findByRoleId(role.getId());
            resourceSet.addAll(list);
        }
        return resourceSet;
    }
}
