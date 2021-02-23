package com.project.cache.service;

import com.project.pojo.Admin;

public interface AdminCacheService {
    Admin findById(String adminId);

    Admin update(Admin admin);
}
