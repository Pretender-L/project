package com.project.system.service.impl;

import com.project.system.dao.LoginLogMapper;
import com.project.system.pojo.LoginLog;
import com.project.system.service.LoginLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Resource
    private LoginLogMapper loginLogMapper;

    /***
     * 查询全部列表
     */
    @Override
    public List<LoginLog> findAll() {
        return loginLogMapper.selectAll();
    }

    /***
     * 根据ID查询
     */
    @Override
    public LoginLog findById(Integer id) {
        return loginLogMapper.selectByPrimaryKey(id);
    }

    /***
     * 增加
     */
    @Override
    public void add(LoginLog loginLog) {
        loginLogMapper.insert(loginLog);
    }

    /***
     * 修改
     */
    @Override
    public void update(LoginLog loginLog) {
        loginLogMapper.updateByPrimaryKey(loginLog);
    }

    /***
     * 删除
     */
    @Override
    public void delete(Integer id) {
        loginLogMapper.deleteByPrimaryKey(id);
    }

    /***
     * 条件查询
     */
    @Override
    public List<LoginLog> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return loginLogMapper.selectByExample(example);
    }

    /***
     * 分页查询
     */
    @Override
    public Page<LoginLog> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        return (Page<LoginLog>) loginLogMapper.selectAll();
    }

    /***
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<LoginLog> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(searchMap);
        return (Page<LoginLog>) loginLogMapper.selectByExample(example);
    }

    /***
     * 构建查询对象
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(LoginLog.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // login_name
            if (searchMap.get("login_name") != null && !"".equals(searchMap.get("login_name"))) {
                criteria.andLike("login_name", "%" + searchMap.get("login_name") + "%");
            }
            // ip
            if (searchMap.get("ip") != null && !"".equals(searchMap.get("ip"))) {
                criteria.andLike("ip", "%" + searchMap.get("ip") + "%");
            }
            // browser_name
            if (searchMap.get("browser_name") != null && !"".equals(searchMap.get("browser_name"))) {
                criteria.andLike("browser_name", "%" + searchMap.get("browser_name") + "%");
            }
            // 地区
            if (searchMap.get("location") != null && !"".equals(searchMap.get("location"))) {
                criteria.andLike("location", "%" + searchMap.get("location") + "%");
            }
            // id
            if (searchMap.get("id") != null) {
                criteria.andEqualTo("id", searchMap.get("id"));
            }
        }
        return example;
    }
}
