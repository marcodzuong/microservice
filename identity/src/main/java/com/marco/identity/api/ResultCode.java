package com.marco.identity.api;

import lombok.Getter;

/**
 * @author MarcoDuong
 */
@Getter
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "SUCCESS"),
    FAILED(500, "FAILED"),
    VALIDATE_FAILED(404, "VALIDATE_FAILED"),
    RESOURCE_NOT_FOUND(500, "RESOURCE_NOT_FOUND"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    FORBIDDEN(403, "FORBIDDEN");

    private final long code;
    private final String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

}
