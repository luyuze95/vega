package com.luyuze.vega.exception.http;

public class Unauthorized extends HttpException {

    public Unauthorized(int code) {
        this.code = code;
        this.httpStatusCode = 401;
    }
}
