package com.luyuze.vega.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = PasswordValidator.class) // 说明一下这个注解使用的验证器是哪个
public @interface PasswordEqual {

    int min() default 6;

    int max() default 20;

    String message() default "passwords are not equal";

    /**
     * 下面这两个方法是注解默认需要的两个方法，只要写注解，直接加上即可
     * @return
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
