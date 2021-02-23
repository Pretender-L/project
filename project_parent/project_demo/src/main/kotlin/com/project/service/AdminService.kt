package com.project.service

import com.project.pojo.Admin
import com.project.pojo.JpaPageInfo
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