package com.project.system.service;

import com.project.system.util.AuthToken;

public interface AuthService {
    AuthToken login(String username, String password, String clientId, String clientSecret);
}
