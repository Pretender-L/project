package com.project.user.service.impl;

import com.project.user.dao.ProvincesMapper;
import com.project.user.service.ProvincesService;
import com.project.user.pojo.Provinces;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ProvincesServiceImpl implements ProvincesService {
    @Resource
    private ProvincesMapper provincesMapper;

    /***
     * 查询全部列表
     */
    @Override
    public List<Provinces> findAll() {
        return provincesMapper.selectAll();
    }

    /***
     * 根据ID查询
     */
    @Override
    public Provinces findById(String provinceid){
        return  provincesMapper.selectByPrimaryKey(provinceid);
    }

    /***
     * 增加
     */
    @Override
    public void add(Provinces provinces){
        provincesMapper.insert(provinces);
    }

    /***
     * 修改
     */
    @Override
    public void update(Provinces provinces){
        provincesMapper.updateByPrimaryKey(provinces);
    }

    /***
     * 删除
     */
    @Override
    public void delete(String provinceid){
        provincesMapper.deleteByPrimaryKey(provinceid);
    }

    /***
     * 条件查询
     */
    @Override
    public List<Provinces> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return provincesMapper.selectByExample(example);
    }

    /***
     * 分页查询
     */
    @Override
    public Page<Provinces> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<Provinces>)provincesMapper.selectAll();
    }

    /***
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Provinces> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<Provinces>)provincesMapper.selectByExample(example);
    }

    /***
     * 构建查询对象
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Provinces.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 省份ID
            if(searchMap.get("provinceid")!=null && !"".equals(searchMap.get("provinceid"))){
                criteria.andLike("provinceid","%"+searchMap.get("provinceid")+"%");
           	}
            // 省份名称
            if(searchMap.get("province")!=null && !"".equals(searchMap.get("province"))){
                criteria.andLike("province","%"+searchMap.get("province")+"%");
           	}
        }
        return example;
    }
}
