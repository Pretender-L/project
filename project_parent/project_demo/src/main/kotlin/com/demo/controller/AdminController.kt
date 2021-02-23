package com.demo.controller

import com.demo.pojo.*
import com.demo.service.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/admin")
class AdminController {

    @Autowired
    private lateinit var adminService: AdminService

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<Any, Any>

    @GetMapping("/findAll")
    fun findAll(): Result<*>? {
        if (redisTemplate.hasKey("findAll")) {
            val adminList = redisTemplate.opsForValue().get("findAll")
            redisTemplate.opsForValue().set("findAll", adminList, 60, TimeUnit.SECONDS)
            return Result.success(adminList)
        }
        val adminList = adminService.findAll()
        return Result.success(adminList)
    }

    @GetMapping("/findPage")
    fun findPage(jpaPageInfo: JpaPageInfo): Result<*> {
        val page = adminService.findPage(jpaPageInfo)
        val pageResult = PageResult<Admin>(page.totalPages.toLong(), page.content)
        return Result.success(pageResult)
    }

    /**
     * 搜索第一种实现
     */
    @GetMapping("/search")
    fun search(loginName: String, status: String): Result<*> {
        val adminList = adminService.search(loginName, status)
        return Result.success(adminList)
    }

    /**
     * 搜索的第二种实现
     */
    @GetMapping("/findBySearch")
    fun findBySearch(loginName: String, status: String, jpaPageInfo: JpaPageInfo): Result<*> {
        val page = adminService.findBySearch(loginName, status, jpaPageInfo)
        val pageResult = PageResult<Admin>(page.totalPages.toLong(), page.content)
        return Result.success(pageResult)
    }

    /**
     * 添加
     */
    @PostMapping("/add")
    fun add(@RequestBody admin: Admin): Result<*>{
        adminService.add(admin)
        return Result.success()
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{adminId}")
    fun delete(@PathVariable adminId:String):Result<*>{
        adminService.delete(adminId)
        return Result.success()
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    fun update(@RequestBody admin: Admin){
        adminService.update(admin)
    }
}