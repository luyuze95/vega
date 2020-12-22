package com.luyuze.vega.core;

import lombok.Data;

@Data
public class UnifyResponse {

    private Integer code;

    private String message;

    private String request;

    public UnifyResponse(int code, String message, String request) {
        this.code = code;
        this.message = message;
        this.request = request;
    }
}
