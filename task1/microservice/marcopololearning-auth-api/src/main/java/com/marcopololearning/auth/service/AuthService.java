package com.marcopololearning.auth.service;

import com.marcopololearning.auth.model.AuthResponse;
import com.marcopololearning.auth.model.UserView;

public interface AuthService {
    AuthResponse authenticate(String username, String password);
    UserView query(String token);
}
