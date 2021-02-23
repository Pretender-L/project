package com.project.service.impl

import com.project.excetion.BadException
import com.project.pojo.Admin
import com.project.pojo.JpaPageInfo
import com.project.repository.AdminRepository
import com.project.service.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

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
        if (!loginName.isNullOrEmpty()) {
            admin.loginName = loginName
            matcher = matcher.withMatcher("loginName", ExampleMatcher.GenericPropertyMatchers.contains())
        }
        if (!status.isNullOrEmpty()) {
            admin.status = status
            matcher = matcher.withMatcher("ststus", ExampleMatcher.GenericPropertyMatchers.exact())
        }
        matcher = matcher.withIgnorePaths("id")
        val example = Example.of(admin, matcher)
        return adminRepository.findAll(example, jpaPageInfo.pageable)
    }


    override fun search(loginName: String, status: String): List<Admin> {
        val specification: Specification<Admin> = object : Specification<Admin> {
            override fun toPredicate(
                root: Root<Admin>,
                criteriaQuery: CriteriaQuery<*>,
                criteriaBuilder: CriteriaBuilder
            ): Predicate? {
                val predicate = criteriaBuilder.conjunction()
                if (!loginName.isNullOrEmpty()) {
                    predicate.expressions.add(criteriaBuilder.like(root.get("loginName"), "%${loginName}%"))
                }
                if (!status.isNullOrEmpty()) {
                    predicate.expressions.add(criteriaBuilder.equal(root.get<String>("status"), status))
                }
                return predicate
            }
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