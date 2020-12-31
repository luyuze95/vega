package com.luyuze.vega.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BannerItem {

    @Id
    private Long id;
    private String img;
    private String keyword;
    private Short type;
    private String name;

    @ManyToOne
    @JoinColumn(name = "bannerId")
    private Banner banner;
}
