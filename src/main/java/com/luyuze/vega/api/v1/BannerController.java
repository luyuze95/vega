package com.luyuze.vega.api.v1;

import com.luyuze.vega.exception.http.ForbiddenException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("banner")
public class BannerController {

    @GetMapping("hello")
    public String test() {
        throw new ForbiddenException(10001);
    }
}
