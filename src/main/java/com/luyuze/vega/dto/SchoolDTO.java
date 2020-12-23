package com.luyuze.vega.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDTO {

    @Length(min = 2, max = 10)
    private String schoolName;
}
