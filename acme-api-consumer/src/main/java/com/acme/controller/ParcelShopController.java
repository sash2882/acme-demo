package com.acme.controller;

import com.acme.model.ParcelShop;
import com.acme.service.ParcelShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParcelShopController {

    @Autowired
    private ParcelShopService parcelShopService;

    public ParcelShopController(ParcelShopService parcelShopService) {
        this.parcelShopService = parcelShopService;
    }

    @GetMapping("/getParcelShops")
    public List<ParcelShop> getParcelShops(
            @RequestParam String carrier,
            @RequestParam String country,
            @RequestParam(defaultValue = "10") int limit) {

        return parcelShopService.performParcelOperation(carrier, country, limit);
    }
}
