package com.luyuze.vega.exception.http;

import lombok.Data;

public class ForbiddenException extends HttpException {

    public ForbiddenException(int code) {
        this.httpStatusCode = 403;
        this.code = code;
    }
}
