package com.luyuze.vega.dto;

import com.luyuze.vega.validators.PasswordEqual;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordEqual(message = "两次密码输入不一致")
public class PersonDTO {

    @Length(min = 2, max = 10, message = "名称的长度必须在2-10之间")
    private String name;

    private Integer age;

    private String password1;
    private String password2;

}
