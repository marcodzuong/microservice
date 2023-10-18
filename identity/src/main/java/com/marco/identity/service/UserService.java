package com.marco.identity.service;

import com.marco.identity.dto.LoginDto;
import com.marco.identity.dto.UserDto;
import com.marco.identity.entities.Authority;
import com.marco.identity.entities.User;
import com.marco.identity.common.exception.ApiException;
import com.marco.identity.repository.AuthorityRepository;
import com.marco.identity.repository.UserRepository;
import com.marco.identity.security.AuthoritiesConstants;
import com.marco.identity.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @Cacheable(cacheNames = "USERS_BY_USERNAME_CACHE")
    public Optional<User> findOneWithAuthoritiesByUserName(String userName) {
        return userRepository.findOneWithAuthoritiesByUserName(userName);
    }

    public Optional<User> findOneByUserName(String userName) {
        return userRepository.findOneByUserName(userName);
    }

    @Transactional
    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils
                .getCurrentUserLogin()
                .flatMap(userRepository::findOneByUserName)
                .ifPresent(user -> {
                    if (!passwordEncoder.matches(currentClearTextPassword,user.getPassword())) {
                        throw new ApiException("Current Password is invalid");
                    }
                    user.setPassword(passwordEncoder.encode(newPassword));
                    clearUserCaches(user.getUserName());
                });
    }

    @CacheEvict("USERS_BY_USERNAME_CACHE")
    public void clearUserCaches(String userName) {
    }

    @Transactional
    public User createUser(LoginDto dto) {
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        Set<Authority> authorities;
        if (dto.getAuthorities() != null) {
            authorities = dto
                    .getAuthorities()
                    .stream()
                    .map(authorityRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        } else {
            authorities = new HashSet<>();
            authorities.add(new Authority(AuthoritiesConstants.USER));
        }
        user.setAuthorities(authorities);
        clearUserCaches(dto.getUserName());
        return userRepository.save(user);
    }

    @Transactional
    public User update(Long id, UserDto dto) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ApiException("User Not Exit");
        }
        user.setAuthorities(
                dto.getAuthorities()
                        .stream()
                        .map(authorityRepository::findById)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toSet())
        );
        clearUserCaches(user.getUserName());
        return userRepository.save(user);
    }

    @Transactional
    public boolean deleteById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ApiException("User Not Exit");
        }
        clearUserCaches(user.getUserName());
        userRepository.delete(user);
        return true;
    }
}
