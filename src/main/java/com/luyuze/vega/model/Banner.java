package com.luyuze.vega.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String img;
    private String title;

    @OneToMany(mappedBy = "banner")
    private List<BannerItem> bannerItems;
}
