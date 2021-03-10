package com.project.demo.jpa.service.impl

import com.project.common.excetion.BadException
import com.project.demo.pojo.Admin
import com.project.demo.pojo.JpaPageInfo
import com.project.demo.jpa.repository.AdminRepository
import com.project.demo.jpa.service.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class AdminServiceImpl : AdminService {
    @Autowired
    private lateinit var adminRepository: AdminRepository

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<Any,Any>

    override fun findAll(): List<Admin> {
        val adminList = adminRepository.findAll()
        redisTemplate.opsForValue().set("findAll",adminList,60,TimeUnit.SECONDS)
        return adminList
    }

    override fun findPage(jpaPageInfo: JpaPageInfo): Page<Admin> {
        return adminRepository.findAll(jpaPageInfo.pageable)
    }

    override fun findBySearch(loginName: String, status: String, jpaPageInfo: JpaPageInfo): Page<Admin> {
        var matcher = ExampleMatcher.matching()
        val admin = Admin()
        if (loginName.isNotEmpty()) {
            admin.loginName = loginName
            matcher = matcher.withMatcher("loginName", ExampleMatcher.GenericPropertyMatchers.contains())
        }
        if (status.isNotEmpty()) {
            admin.status = status
            matcher = matcher.withMatcher("status", ExampleMatcher.GenericPropertyMatchers.exact())
        }
        matcher = matcher.withIgnorePaths("id")
        val example = Example.of(admin, matcher)
        return adminRepository.findAll(example, jpaPageInfo.pageable)
    }


    override fun search(loginName: String, status: String): List<Admin> {
        val specification: Specification<Admin> =
            Specification<Admin> { root, _, criteriaBuilder ->
                val predicate = criteriaBuilder.conjunction()
                if (loginName.isNotEmpty()) {
                    predicate.expressions.add(criteriaBuilder.like(root.get("loginName"), "%${loginName}%"))
                }
                if (status.isNotEmpty()) {
                    predicate.expressions.add(criteriaBuilder.equal(root.get<String>("status"), status))
                }
                predicate
            }
        return adminRepository.findAll(specification)
    }

    override fun add(admin: Admin) {
        adminRepository.save(admin)
    }

    override fun delete(adminId: String) {
        adminRepository.deleteById(adminId)
    }

    override fun update(admin: Admin) {
        if (admin.id.isEmpty()){
            throw BadException("数据非法!")
        }
        adminRepository.save(admin)
    }
}