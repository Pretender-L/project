package com.demo.cache.service;

import com.demo.pojo.Admin;

public interface AdminCacheService {

    Admin findById(String adminId);

    Admin update(Admin admin);
}
