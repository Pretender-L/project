package com.project.demo.jpa.controller

import com.project.common.entity.PageResult
import com.project.demo.pojo.*
import com.project.demo.jpa.service.AdminService
import com.project.common.entity.Result
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit
import javax.annotation.Resource

@RestController
@RequestMapping("/admin")
open class AdminController {
    @Resource
    private lateinit var adminService: AdminService

    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    @GetMapping("/findAll")
    fun findAll(): Result<Any>? {
        if (redisTemplate.hasKey("findAll")) {
            val adminList = redisTemplate.opsForValue().get("findAll")
            redisTemplate.opsForValue().set("findAll", adminList!!, 60, TimeUnit.SECONDS)
            return Result<Any>().success(adminList)
        }
        val adminList = adminService.findAll()
        return Result<Any>().success(adminList)
    }

    @GetMapping("/findPage")
    fun findPage(jpaPageInfo: JpaPageInfo): Result<Any> {
        val page = adminService.findPage(jpaPageInfo)
        val pageResult = PageResult(page.totalPages.toLong(), page.content)
        return Result<Any>().success(pageResult)
    }

    /**
     * 搜索第一种实现
     */
    @GetMapping("/search")
    fun search(loginName: String, status: String): Result<Any> {
        val adminList = adminService.search(loginName, status)
        return Result<Any>().success(adminList)
    }

    /**
     * 搜索的第二种实现
     */
    @GetMapping("/findBySearch")
    fun findBySearch(loginName: String, status: String, jpaPageInfo: JpaPageInfo): Result<Any> {
        val page = adminService.findBySearch(loginName, status, jpaPageInfo)
        val pageResult = PageResult(page.totalPages.toLong(), page.content)
        return Result<Any>().success(pageResult)
    }

    /**
     * 添加
     */
    @PostMapping("/add")
    fun add(@RequestBody admin: Admin): Result<*>{
        adminService.add(admin)
        return Result<Any>().success()
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{adminId}")
    fun delete(@PathVariable adminId:String):Result<*>{
        adminService.delete(adminId)
        return Result<Any>().success()
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    fun update(@RequestBody admin: Admin){
        adminService.update(admin)
    }
}