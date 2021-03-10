package com.project.demo.cache.repository;

import com.project.demo.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminCacheRepository extends JpaRepository<Admin, String> {
}
