package com.project.demo.cache.service;

import com.project.demo.pojo.Admin;

public interface AdminCacheService {
    Admin findById(String adminId);

    Admin update(Admin admin);
}
