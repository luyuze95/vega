package com.luyuze.vega.service.impl;

import com.luyuze.vega.model.Banner;
import com.luyuze.vega.repository.BannerRepository;
import com.luyuze.vega.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public Banner getByName(String name) {
        return bannerRepository.findOneByName(name);
    }
}
