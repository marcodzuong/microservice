package com.marco.identity.security;

import com.marco.identity.util.HttpUtil;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenUtil;

    public JwtFilter(JwtTokenService jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        String token = HttpUtil.getToken(request);
        if (ObjectUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String userName = jwtTokenUtil.getUserNameFromToken(token);
        String authoritiesStr = jwtTokenUtil.getAuthorities(token);
        setAuthenticationContext(userName, authoritiesStr, token,request);
        filterChain.doFilter(request, response);
    }

    private void setAuthenticationContext(String userName, String authoritiesStr, String token,HttpServletRequest request) {
        Collection<? extends GrantedAuthority> authorities = authoritiesFromStr(authoritiesStr);
        UserDetails userDetails = new User(userName, "", authorities);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Collection<? extends GrantedAuthority> authoritiesFromStr(String str) {
        if (ObjectUtils.isEmpty(str)) return new ArrayList<>();
        return Arrays
                .stream(str.split(","))
                .filter(auth -> !auth.trim().isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


}
