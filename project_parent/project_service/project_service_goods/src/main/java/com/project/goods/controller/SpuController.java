package com.project.goods.controller;

import com.github.pagehelper.Page;
import com.project.entity.PageResult;
import com.project.entity.Result;
import com.project.goods.pojo.Goods;
import com.project.goods.pojo.Spu;
import com.project.goods.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/spu")
public class SpuController {

    @Autowired
    private SpuService spuService;

    /***
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll() {
        List<Spu> spuList = spuService.findAll();
        return Result.success("查询成功", spuList);
    }

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable(value = "id") String id) {
        //旧方法
        /*Spu spu = spuService.findById(id);*/
        Goods goods = spuService.findGoodsById(id);
        return Result.success("查询成功", goods);
    }

    @GetMapping("/findSpuById/{id}")
    public Result<Spu> findSpuById(@PathVariable(value = "id") String id) {
        Spu spu = spuService.findById(id);
        return Result.success("查询成功", spu);
    }

    /***
     * 新增数据
     * @param goods
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Goods goods) {
        spuService.add(goods);
        return Result.success("添加成功");
    }

    /***
     * 修改数据
     * @param goods
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Goods goods, @PathVariable(value = "id") String id) {
        spuService.update(goods);
        return Result.success("修改成功");
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable(value = "id") String id) {
        spuService.delete(id);
        return Result.success("删除成功");
    }

    /***
     * 多条件搜索品牌数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap) {
        List<Spu> list = spuService.findList(searchMap);
        return Result.success("查询成功", list);
    }

    /***
     * 分页搜索实现
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
        Page<Spu> pageList = spuService.findPage(searchMap, page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return Result.success(pageResult);
    }

    /***
     * 商品审核自动上架
     * @param id
     */
    @PutMapping("/audit/{id}")
    public Result audit(@PathVariable(value = "id") String id) {
        spuService.audit(id);
        return Result.success("商品审核成功");
    }

    /**
     * 商品下架
     */
    @PutMapping("/pull/{id}")
    public Result pull(@PathVariable(value = "id") String id) {
        spuService.pull(id);
        return Result.success("商品下架成功");
    }

    /**
     * 商品上架
     */
    @PutMapping("/put/{id}")
    public Result put(@PathVariable(value = "id") String id) {
        spuService.put(id);
        return Result.success("商品上架成功");
    }

    /***
     * 还原商品
     * @param id
     * @return
     */
    @PutMapping("/restore/{id}")
    public Result restore(@PathVariable(value = "id") String id) {
        spuService.restore(id);
        return Result.success("商品还原成功");
    }

    @DeleteMapping("/realDel/{id}")
    public Result realDel(@PathVariable(value = "id") String id) {
        spuService.realDel(id);
        return Result.success("商品永久删除成功");
    }

}
