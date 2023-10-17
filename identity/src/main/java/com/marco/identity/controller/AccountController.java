package com.marco.identity.controller;

import com.marco.identity.api.CommonResult;
import com.marco.identity.api.IErrorCode;
import com.marco.identity.api.ResultCode;
import com.marco.identity.dto.LoginDto;
import com.marco.identity.entities.User;
import com.marco.identity.exception.ApiException;
import com.marco.identity.security.JwtTokenService;
import com.marco.identity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final JwtTokenService jwtTokenService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountController(JwtTokenService jwtTokenService,
                             UserService userService,
                             PasswordEncoder passwordEncoder) {
        this.jwtTokenService = jwtTokenService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public Object login(@RequestBody @Valid LoginDto request) {
        User user = userService.findOneWithAuthoritiesByUserName(request.getUserName()).orElse(null);
        if (user == null) {
            throw new ApiException("Account not exit");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new ApiException("Password is invalid!");
        }
        Map<String, Object> mapRes = new HashMap<>();
        mapRes.put("token", jwtTokenService.generateAccessToken(user));
        return CommonResult.success(mapRes);
    }

    @PostMapping("/register")
    public Object register(@RequestBody @Valid LoginDto request) {
        User user = userService.findOneByUserName(request.getUserName()).orElse(null);
        if (user != null) {
            throw new ApiException("Account Already");
        }
        return CommonResult.success(userService.createUser(request));
    }

}
