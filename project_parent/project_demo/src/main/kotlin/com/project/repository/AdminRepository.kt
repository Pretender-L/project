package com.demo.repository

import com.demo.pojo.Admin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface AdminRepository : JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {
}