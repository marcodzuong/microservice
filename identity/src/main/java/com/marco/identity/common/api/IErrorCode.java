package com.marco.identity.common.api;

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
