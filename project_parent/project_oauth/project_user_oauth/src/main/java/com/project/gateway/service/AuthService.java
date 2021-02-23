package com.project.gateway.service;

import com.project.gateway.util.AuthToken;

public interface AuthService {
    AuthToken login(String username, String password, String clientId, String clientSecret);
}
