package com.project.user.service.impl;

import com.project.user.dao.AreasMapper;
import com.project.user.service.AreasService;
import com.project.user.pojo.Areas;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AreasServiceImpl implements AreasService {
    @Resource
    private AreasMapper areasMapper;

    /***
     * 查询全部列表
     */
    @Override
    public List<Areas> findAll() {
        return areasMapper.selectAll();
    }

    /***
     * 根据ID查询
     */
    @Override
    public Areas findById(String areaid) {
        return areasMapper.selectByPrimaryKey(areaid);
    }

    /***
     * 增加
     */
    @Override
    public void add(Areas areas) {
        areasMapper.insert(areas);
    }

    /***
     * 修改
     */
    @Override
    public void update(Areas areas) {
        areasMapper.updateByPrimaryKey(areas);
    }

    /***
     * 删除
     */
    @Override
    public void delete(String areaid) {
        areasMapper.deleteByPrimaryKey(areaid);
    }

    /***
     * 条件查询
     */
    @Override
    public List<Areas> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return areasMapper.selectByExample(example);
    }

    /***
     * 分页查询
     */
    @Override
    public Page<Areas> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        return (Page<Areas>) areasMapper.selectAll();
    }

    /***
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Areas> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(searchMap);
        return (Page<Areas>) areasMapper.selectByExample(example);
    }

    /***
     * 构建查询对象
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Areas.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // 区域ID
            if (searchMap.get("areaid") != null && !"".equals(searchMap.get("areaid"))) {
                criteria.andLike("areaid", "%" + searchMap.get("areaid") + "%");
            }
            // 区域名称
            if (searchMap.get("area") != null && !"".equals(searchMap.get("area"))) {
                criteria.andLike("area", "%" + searchMap.get("area") + "%");
            }
            // 城市ID
            if (searchMap.get("cityid") != null && !"".equals(searchMap.get("cityid"))) {
                criteria.andLike("cityid", "%" + searchMap.get("cityid") + "%");
            }
        }
        return example;
    }
}
