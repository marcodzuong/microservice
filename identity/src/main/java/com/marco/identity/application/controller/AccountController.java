package com.marco.identity.application.controller;

import com.marco.identity.application.api.CommonResult;
import com.marco.identity.application.dto.LoginDto;
import com.marco.identity.application.dto.PasswordChangeDTO;
import com.marco.identity.infrastructure.entities.UserEntity;
import com.marco.identity.util.exception.ApiException;
import com.marco.identity.application.security.JwtTokenService;
import com.marco.identity.domain.identity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
        UserEntity user = userService.findOneWithAuthoritiesByUserName(request.getUserName()).orElse(null);
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


    @PostMapping("/change-password")
    public Object changePassword(@RequestBody @Valid PasswordChangeDTO dto){
        userService.changePassword(dto.getCurrentPassword(), dto.getNewPassword());
        return CommonResult.success();
    }


    @PostMapping("/register")
    public Object register(@RequestBody @Valid LoginDto request) {
        UserEntity user = userService.findOneByUserName(request.getUserName()).orElse(null);
        if (user != null) {
            throw new ApiException("Account Already");
        }
        return CommonResult.success(userService.createUser(request));
    }

}
