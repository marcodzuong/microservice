package com.marco.identity.service;

import com.marco.identity.entities.User;
import com.marco.identity.repository.UserRepository;
import com.marco.identity.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CacheManager cacheManager;
    @Autowired
    public UserService(UserRepository userRepository,
            CacheManager cacheManager){
        this.userRepository = userRepository;
        this.cacheManager = cacheManager;
    }

    @Cacheable(cacheNames = "USERS_BY_USERNAME_CACHE")
    public Optional<User> findOneWithAuthoritiesByUserName(String userName){
        return userRepository.findOneWithAuthoritiesByUserName(userName);
    }
    @Transactional
    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils
                .getCurrentUserLogin()
                .flatMap(userRepository::findOneByUserName)
                .ifPresent(user -> {
                    if (!currentClearTextPassword.equals(user.getPassword())) {
                        throw new RuntimeException("");
                    }
                    user.setPassword(newPassword);
                    this.clearUserCaches(user);
                });
    }

    private void clearUserCaches(User user) {
        Objects.requireNonNull(cacheManager.getCache("USERS_BY_USERNAME_CACHE")).evict(user.getUserName());
    }
}
