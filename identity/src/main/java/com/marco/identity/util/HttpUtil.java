package com.marco.identity.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.ObjectUtils;

public class HttpUtil {
    public static String getToken(HttpServletRequest request) {
        String token;
        if (!hasAuthorizationBearer(request)){
            return null;
        }
        token = request.getHeader("Authorization").split(" ")[1].trim();
        return token;
    }

    private static boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return !ObjectUtils.isEmpty(header) && header.startsWith("Bearer");
    }

}
