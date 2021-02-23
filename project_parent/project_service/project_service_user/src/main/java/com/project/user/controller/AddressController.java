package com.project.user.controller;

import com.github.pagehelper.Page;
import com.project.entity.PageResult;
import com.project.entity.Result;
import com.project.user.config.TokenDecode;
import com.project.user.pojo.Address;
import com.project.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private TokenDecode tokenDecode;

    /***
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll() {
        List<Address> addressList = addressService.findAll();
        return Result.success("查询成功", addressList);
    }

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        Address address = addressService.findById(id);
        return Result.success("查询成功", address);
    }

    /***
     * 新增数据
     * @param address
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Address address) {
        addressService.add(address);
        return Result.success("添加成功");
    }

    /***
     * 修改数据
     * @param address
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Address address, @PathVariable Integer id) {
        address.setId(id);
        addressService.update(address);
        return Result.success("修改成功");
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        addressService.delete(id);
        return Result.success("删除成功");
    }

    /***
     * 多条件搜索品牌数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap) {
        List<Address> list = addressService.findList(searchMap);
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
    public Result findPage(@RequestParam Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Address> pageList = addressService.findPage(searchMap, page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return Result.success("查询成功", pageResult);
    }

    @GetMapping(value = "/list")
    public Result<List<Address>> list() {
        //获得当前登录人
        String username = tokenDecode.getUserInfo().get("username");
        List<Address> addressList = addressService.list(username);
        return Result.success("查询成功", addressList);
    }

}
