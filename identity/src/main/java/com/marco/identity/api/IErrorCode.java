package com.marco.identity.api;

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
