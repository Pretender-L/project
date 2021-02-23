package com.project.goods.controller;

import com.github.pagehelper.Page;
import com.project.entity.PageResult;
import com.project.entity.Result;
import com.project.goods.pojo.Sku;
import com.project.goods.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/sku")
public class SkuController {

    @Autowired
    private SkuService skuService;

    /***
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll() {
        List<Sku> skuList = skuService.findAll();
        return Result.success("查询成功", skuList);
    }

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Sku> findById(@PathVariable(value = "id") String id) {
        Sku sku = skuService.findById(id);
        return Result.success("查询成功", sku);
    }

    /***
     * 新增数据
     * @param sku
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Sku sku) {
        skuService.add(sku);
        return Result.success("添加成功");
    }

    /***
     * 修改数据
     * @param sku
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Sku sku, @PathVariable(value = "id") String id) {
        sku.setId(id);
        skuService.update(sku);
        return Result.success("修改成功");
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable(value = "id") String id) {
        skuService.delete(id);
        return Result.success("删除成功");
    }

    /***
     * 多条件搜索品牌数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap) {
        List<Sku> list = skuService.findList(searchMap);
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
        Page<Sku> pageList = skuService.findPage(searchMap, page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return Result.success("查询成功", pageResult);
    }

    /***
     * 根据spuId查询所有的sku（商品状态正常），或传all查询所有的sku（商品状态正常）
     * @param spuId
     * @return
     */
    @GetMapping("/spu/{spuId}")
    public List<Sku> findSkuListBySpuId(@PathVariable(value = "spuId") String spuId) {
        Map<String, Object> searchMap = new HashMap<>();
        if (!"all".equals(spuId)) {
            searchMap.put("spuId", spuId);
        }
        //商品状态正常
        searchMap.put("status", "1");
        List<Sku> skuList = skuService.findList(searchMap);
        return skuList;
    }

    @PostMapping("/decr/count")
    public Result decrCount(@RequestParam(value = "username") String username) {
        skuService.decrCount(username);
        return Result.success("库存扣减成功");
    }

    @RequestMapping("/resumeStockNum")
    public Result resumeStockNum(@RequestParam("skuId") String skuId, @RequestParam("num") Integer num) {
        skuService.resumeStockNum(skuId, num);
        return Result.success("库存回滚成功");
    }

}
