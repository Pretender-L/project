package com.demo.cache.repository;

import com.demo.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminCacheRepository extends JpaRepository<Admin,String>{

}
