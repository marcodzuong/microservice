package com.marco.identity.controller;

import com.marco.identity.common.api.CommonResult;
import com.marco.identity.security.AuthoritiesConstants;
import com.marco.identity.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authorities")
public class AuthorityController {

    private final AuthorityService authorityService;

    @Autowired
    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    @GetMapping("/list")
    public Object list() {
        return CommonResult.success(authorityService.findAll());
    }

    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    @PostMapping("/add")
    public Object add(){
        return CommonResult.success();
    }

}
