package com.project.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.project.goods.dao.*;
import com.project.goods.pojo.*;
import com.project.goods.service.SpuService;
import com.project.util.IdWorker;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    /***
     * 查询全部列表
     * @return
     */
    @Override
    public List<Spu> findAll() {
        return spuMapper.selectAll();
    }

    /***
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public Spu findById(String id) {
        return spuMapper.selectByPrimaryKey(id);
    }

    /***
     * 增加
     * @param goods
     */
    @Transactional
    @Override
    public void add(Goods goods) {
        //添加spu
        Spu spu = goods.getSpu();
        //设置分布式id
        long id = idWorker.nextId();
        spu.setId(String.valueOf(id));
        //设置删除状态(0为否)
        spu.setIsDelete("0");
        //设置上架状态(0为否)
        spu.setIsMarketable("0");
        //设置审核状态
        spu.setStatus("0");
        spuMapper.insert(spu);
        this.saveSkuList(goods);
    }

    //增加sku
    private void saveSkuList(Goods goods) {
        Spu spu = goods.getSpu();
        //查询分类对象
        Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());
        //查询品牌对象
        Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
        //设置品牌和分类的关联关系
        //查询关联表
        CategoryBrand categoryBrand = new CategoryBrand();
        categoryBrand.setCategoryId(spu.getCategory3Id());
        categoryBrand.setBrandId(spu.getBrandId());
        int count = categoryBrandMapper.selectCount(categoryBrand);
        //添加关系
        if (count == 0) {
            categoryBrandMapper.insert(categoryBrand);
        }
        List<Sku> skuList = goods.getSkuList();
        if (skuList != null) {
            for (Sku sku : skuList) {
                //设置sku id
                sku.setId(String.valueOf(idWorker.nextId()));
                //设置sku规格数据
                if (StringUtils.isEmpty(sku.getSpec())) {
                    sku.setSpec("{}");
                }
                //设置sku名称（spu名称+sku规格）
                String name = spu.getName();
                System.out.println("name = " + name);
                Map<String, String> specMap = JSON.parseObject(sku.getSpec(), Map.class);
                if (specMap != null && specMap.size() > 0) {
                    for (String value : specMap.values()) {
                        name += "" + value;
                    }
                }
                sku.setName(name);
                //设置spu id
                sku.setSpuId(spu.getId());
                //设置创建与修改的时间
                sku.setCreateTime(new Date());
                sku.setUpdateTime(new Date());
                //商品分类id(商品从属3级分类)
                sku.setCategoryId(category.getId());
                //设置商品分类姓名
                sku.setCategoryName(category.getName());
                //设置品牌名称
                sku.setBrandName(brand.getName());
                //sku添加数据库
                skuMapper.insertSelective(sku);

            }
        }
    }

    /***
     * 修改
     * @param goods
     */
    @Transactional
    @Override
    public void update(Goods goods) {
        //修改spu
        Spu spu = goods.getSpu();
        spuMapper.updateByPrimaryKey(spu);
        //修改sku：删除原有的sku
        Example example = new Example(Sku.class);
        Example.Criteria criteria = example.createCriteria();
        //条件删除
        criteria.andEqualTo("spuId", spu.getId());
        skuMapper.deleteByExample(example);
        //重新添加sku
        this.saveSkuList(goods);

    }

    /***
     * 删除
     * @param id
     */
    @Override
    @Transactional
    public void delete(String id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);
        if (!"0".equals(spu.getIsMarketable())) {
            throw new RuntimeException("当前商品必须处于下架状态才能删除");
        }
        spu.setIsDelete("1");
        spu.setStatus("0");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /***
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Spu> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return spuMapper.selectByExample(example);
    }

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Spu> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        return (Page<Spu>) spuMapper.selectAll();
    }

    /***
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @Override
    public Page<Spu> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(searchMap);
        return (Page<Spu>) spuMapper.selectByExample(example);
    }

    /***
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // 主键
            if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                criteria.andEqualTo("id", searchMap.get("id"));
            }
            // 货号
            if (searchMap.get("sn") != null && !"".equals(searchMap.get("sn"))) {
                criteria.andEqualTo("sn", searchMap.get("sn"));
            }
            // SPU名
            if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                criteria.andLike("name", "%" + searchMap.get("name") + "%");
            }
            // 副标题
            if (searchMap.get("caption") != null && !"".equals(searchMap.get("caption"))) {
                criteria.andLike("caption", "%" + searchMap.get("caption") + "%");
            }
            // 图片
            if (searchMap.get("image") != null && !"".equals(searchMap.get("image"))) {
                criteria.andLike("image", "%" + searchMap.get("image") + "%");
            }
            // 图片列表
            if (searchMap.get("images") != null && !"".equals(searchMap.get("images"))) {
                criteria.andLike("images", "%" + searchMap.get("images") + "%");
            }
            // 售后服务
            if (searchMap.get("saleService") != null && !"".equals(searchMap.get("saleService"))) {
                criteria.andLike("saleService", "%" + searchMap.get("saleService") + "%");
            }
            // 介绍
            if (searchMap.get("introduction") != null && !"".equals(searchMap.get("introduction"))) {
                criteria.andLike("introduction", "%" + searchMap.get("introduction") + "%");
            }
            // 规格列表
            if (searchMap.get("specItems") != null && !"".equals(searchMap.get("specItems"))) {
                criteria.andLike("specItems", "%" + searchMap.get("specItems") + "%");
            }
            // 参数列表
            if (searchMap.get("paraItems") != null && !"".equals(searchMap.get("paraItems"))) {
                criteria.andLike("paraItems", "%" + searchMap.get("paraItems") + "%");
            }
            // 是否上架
            if (searchMap.get("isMarketable") != null && !"".equals(searchMap.get("isMarketable"))) {
                criteria.andEqualTo("isMarketable", searchMap.get("isMarketable"));
            }
            // 是否启用规格
            if (searchMap.get("isEnableSpec") != null && !"".equals(searchMap.get("isEnableSpec"))) {
                criteria.andEqualTo("isEnableSpec", searchMap.get("isEnableSpec"));
            }
            // 是否删除
            if (searchMap.get("isDelete") != null && !"".equals(searchMap.get("isDelete"))) {
                criteria.andEqualTo("isDelete", searchMap.get("isDelete"));
            }
            // 审核状态
            if (searchMap.get("status") != null && !"".equals(searchMap.get("status"))) {
                criteria.andEqualTo("status", searchMap.get("status"));
            }
            // 品牌ID
            if (searchMap.get("brandId") != null) {
                criteria.andEqualTo("brandId", searchMap.get("brandId"));
            }
            // 一级分类
            if (searchMap.get("category1Id") != null) {
                criteria.andEqualTo("category1Id", searchMap.get("category1Id"));
            }
            // 二级分类
            if (searchMap.get("category2Id") != null) {
                criteria.andEqualTo("category2Id", searchMap.get("category2Id"));
            }
            // 三级分类
            if (searchMap.get("category3Id") != null) {
                criteria.andEqualTo("category3Id", searchMap.get("category3Id"));
            }
            // 模板ID
            if (searchMap.get("templateId") != null) {
                criteria.andEqualTo("templateId", searchMap.get("templateId"));
            }
            // 运费模板id
            if (searchMap.get("freightId") != null) {
                criteria.andEqualTo("freightId", searchMap.get("freightId"));
            }
            // 销量
            if (searchMap.get("saleNum") != null) {
                criteria.andEqualTo("saleNum", searchMap.get("saleNum"));
            }
            // 评论数
            if (searchMap.get("commentNum") != null) {
                criteria.andEqualTo("commentNum", searchMap.get("commentNum"));
            }
        }
        return example;
    }

    /***
     * 根据id查询商品
     * @param id
     * @return
     */
    @Override
    public Goods findGoodsById(String id) {
        Goods goods = new Goods();
        Spu spu = spuMapper.selectByPrimaryKey(id);
        goods.setSpu(spu);
        Example example = new Example(Sku.class);
        Example.Criteria criteria = example.createCriteria();
        //根据spu进行sku列表的查询
        criteria.andEqualTo("spuId", id);
        List<Sku> skuList = skuMapper.selectByExample(example);
        goods.setSkuList(skuList);
        return goods;
    }

    /***
     * 商品审核自动上架
     * @param id
     */
    @Override
    @Transactional
    public void audit(String id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);
        if (spu == null) {
            throw new RuntimeException("当前商品不存在");
        }
        //0表示否，1表示是
        if ("1".equals(spu.getIsDelete())) {
            throw new RuntimeException("当前商品处于删除状态");
        }
        spu.setStatus("1");
        spu.setIsMarketable("1");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /***
     * 商品下架
     * @param id
     */
    @Override
    @Transactional
    public void pull(String id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);
        if (spu == null) {
            throw new RuntimeException("当前商品不存在");
        }
        //0表示否，1表示是
        if ("1".equals(spu.getIsDelete())) {
            throw new RuntimeException("当前商品处于删除状态");
        }
        spu.setIsMarketable("0");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /***
     * 商品上架
     */
    @Override
    public void put(String id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);
        if (spu == null) {
            throw new RuntimeException("当前商品不存在");
        }
        if (!"1".equals(spu.getStatus())) {
            throw new RuntimeException("当卡商品未审核");
        }
        spu.setIsMarketable("1");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /***
     * 还原商品
     * @param id
     */
    @Override
    @Transactional
    public void restore(String id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);
        if (!"1".equals(spu.getIsDelete())) {
            throw new RuntimeException("此商品未删除");
        }
        spu.setIsDelete("0");
        spu.setStatus("0");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    @Override
    @Transactional
    public void realDel(String id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);
        if (!"1".equals(spu.getIsDelete())) {
            throw new RuntimeException("此商品处于未删除状态");
        }
        spuMapper.deleteByPrimaryKey(id);
    }

}
