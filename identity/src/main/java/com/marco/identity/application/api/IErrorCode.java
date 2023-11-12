package com.marco.identity.application.api;

/**
 * @author MarcoDuong
 */
public interface IErrorCode {
    /**
     * return code
     */
    long getCode();

    /**
     * return messages
     */
    String getMessage();
}
