package com.project.goods.controller;

import com.github.pagehelper.Page;
import com.project.entity.PageResult;
import com.project.entity.Result;
import com.project.goods.pojo.Album;
import com.project.goods.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    /***
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll() {
        List<Album> albumList = albumService.findAll();
        return Result.success("查询成功", albumList);
    }

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Long id) {
        Album album = albumService.findById(id);
        return Result.success("查询成功", album);
    }

    /***
     * 新增数据
     * @param album
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Album album) {
        albumService.add(album);
        return Result.success("添加成功");
    }

    /***
     * 修改数据
     * @param album
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Album album, @PathVariable Long id) {
        album.setId(id);
        albumService.update(album);
        return Result.success("修改成功");
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id) {
        albumService.delete(id);
        return Result.success("删除成功");
    }

    /***
     * 多条件搜索品牌数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap) {
        List<Album> list = albumService.findList(searchMap);
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
        Page<Album> pageList = albumService.findPage(searchMap, page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return Result.success("查询成功", pageResult);
    }

}
