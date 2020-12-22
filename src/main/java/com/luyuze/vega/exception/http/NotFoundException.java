package com.luyuze.vega.exception.http;

import lombok.Data;

public class NotFoundException extends HttpException {

    public NotFoundException(int code) {
        this.httpStatusCode = 404;
        this.code = code;
    }
}
