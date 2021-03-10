package com.project.demo.cache.service;

import com.project.demo.pojo.Admin;

public interface AdminCacheService {
    Admin findById(String adminId);

    void update(Admin admin);
}
