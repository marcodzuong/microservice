package com.marco.identity.domain.identity.service;

import com.marco.identity.application.dto.LoginDto;
import com.marco.identity.application.dto.UserDto;
import com.marco.identity.infrastructure.entities.AuthorityEntity;
import com.marco.identity.infrastructure.entities.UserEntity;
import com.marco.identity.util.exception.ApiException;
import com.marco.identity.infrastructure.dao.AuthorityDao;
import com.marco.identity.infrastructure.dao.UserDao;
import com.marco.identity.util.constant.AuthoritiesConstants;
import com.marco.identity.util.SecurityUtils;
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
    private final UserDao userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityDao authorityRepository;

    @Autowired
    public UserService(UserDao userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthorityDao authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @Cacheable(cacheNames = "USERS_BY_USERNAME_CACHE")
    public Optional<UserEntity> findOneWithAuthoritiesByUserName(String userName) {
        return userRepository.findOneWithAuthoritiesByUserName(userName);
    }

    public Optional<UserEntity> findOneByUserName(String userName) {
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
    public UserEntity createUser(LoginDto dto) {
        UserEntity user = new UserEntity();
        user.setUserName(dto.getUserName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        Set<AuthorityEntity> authorities;
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
            authorities.add(new AuthorityEntity(AuthoritiesConstants.USER));
        }
        user.setAuthorities(authorities);
        clearUserCaches(dto.getUserName());
        return userRepository.save(user);
    }

    @Transactional
    public UserEntity update(Long id, UserDto dto) {
        UserEntity user = userRepository.findById(id).orElse(null);
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
        UserEntity user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ApiException("User Not Exit");
        }
        clearUserCaches(user.getUserName());
        userRepository.delete(user);
        return true;
    }
}
