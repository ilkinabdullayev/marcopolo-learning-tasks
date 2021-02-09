package com.marcopololearning.auth.service.implementation;

import com.marcopololearning.auth.jwt.JwtToken;
import com.marcopololearning.auth.mapper.UserMapper;
import com.marcopololearning.auth.model.UserView;
import com.marcopololearning.auth.repository.UserRepository;
import com.marcopololearning.auth.service.UserService;
import com.marcopololearning.auth.exception.InvalidUsernameOrPasswordException;
import com.marcopololearning.auth.exception.UserNotFoundException;
import com.marcopololearning.auth.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;



@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceDefaultProvider implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;


    @Override
    public User authenticate(String username, String password) {
        log.info("filtering user {}", username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username + " user not found"));

        log.info("ss {}" , BCrypt.hashpw(password, BCrypt.gensalt()));
        if(!BCrypt.checkpw(password, user.getPassword())) {
            log.warn("Authentication failed for user {}", username);
            throw new InvalidUsernameOrPasswordException("Username or password is invalid");
        }

        return user;
    }

    @Override
    public UserView findUserByUsername(String username) {
        log.info("filtering user {}", username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username + " user not found"));
        return userMapper.entityToView(user);
    }


}
