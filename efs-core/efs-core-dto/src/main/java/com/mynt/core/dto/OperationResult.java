package com.mynt.core.dto;

import org.springframework.http.HttpStatus;

/**
 *
 * @author mbmartinez, Sep 27, 2017
 *
 * @param <T> - Data type
 */
public class OperationResult<T> {

    private String message;
    private HttpStatus status;
    private T data;

    public OperationResult() {
    }
    public OperationResult(T data) {
        this.data = data;
        this.status = HttpStatus.OK;
        this.message = "OK!";
    }
    public OperationResult<T> withMessage(String message) {
        this.message = message;
        return this;
    }
    public OperationResult<T> withStatus(HttpStatus status) {
        this.status = status;
        return this;
    }
    public OperationResult<T> withData(T data) {
        this.data = data;
        return this;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public HttpStatus getStatus() {
        if (status == null) {
            status = HttpStatus.OK;
        }
        return status;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
