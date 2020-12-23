package com.luyuze.vega.api.v1;

import com.luyuze.vega.dto.PersonDTO;
import com.luyuze.vega.exception.http.ForbiddenException;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("banner")
@Validated
public class BannerController {

    @PostMapping("hello/{id}")
    public String test(@PathVariable @Range(min = 1, max = 10, message = "id必须在1-10之间") Integer id,
                       @RequestParam @Length(min = 6, message = "名称长度至少为6") String name,
                       @RequestBody @Validated PersonDTO person) {
        throw new ForbiddenException(10001);
    }
}
