package com.demo.service

import com.demo.pojo.Admin
import com.demo.pojo.JpaPageInfo
import com.demo.pojo.PageInfo
import org.springframework.data.domain.Page

interface AdminService {

    fun findAll(): List<Admin>

    fun search(loginName: String, status: String): List<Admin>

    fun findPage(jpaPageInfo: JpaPageInfo): Page<Admin>

    fun findBySearch(loginName: String, status: String, jpaPageInfo: JpaPageInfo): Page<Admin>

    fun add(admin: Admin)

    fun delete(adminId: String)

    fun update(admin: Admin)

}