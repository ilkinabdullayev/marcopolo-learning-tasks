package com.marcopololearning.auth.service;

import com.marcopololearning.auth.entity.User;
import com.marcopololearning.auth.model.UserView;

public interface UserService {
    User authenticate(String username, String password);
    UserView findUserByUsername(String username);
}
