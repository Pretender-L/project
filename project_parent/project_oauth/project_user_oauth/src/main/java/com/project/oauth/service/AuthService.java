package com.project.oauth.service;

import com.project.oauth.util.AuthToken;

public interface AuthService {
    AuthToken login(String username, String password, String clientId, String clientSecret);
}
