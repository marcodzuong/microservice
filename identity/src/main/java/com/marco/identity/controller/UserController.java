package com.marco.identity.controller;

import com.marco.identity.api.CommonResult;
import com.marco.identity.dto.UserDto;
import com.marco.identity.security.AuthoritiesConstants;
import com.marco.identity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    @GetMapping("/list")
    public Object list() {

        return null;
    }

    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    @PutMapping("/{id}")
    public Object updateRole(@PathVariable Long id, @RequestBody @Valid UserDto userDto) {
        return CommonResult.success(userService.update(id,userDto));
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Long id) {
        return CommonResult.success(userService.deleteById(id));
    }


}
