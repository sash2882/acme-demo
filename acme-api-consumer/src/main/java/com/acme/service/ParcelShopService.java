package com.acme.service;

import com.acme.client.ApiClient;
import com.acme.model.ParcelShop;
import com.acme.repository.ParcelShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelShopService {
    @Autowired
    private ApiClient apiClient;
    @Autowired
    private ParcelShopRepository parcelShopRepository;

    public List<ParcelShop> performParcelOperation(String carrier, String country, int limit) {

        // Step 1: Login & fetch access token
        String accessToken = apiClient.login().getAccessToken();

        // Step 2: fetch Parcel Shops using the access token
        List<ParcelShop> parcelShops = apiClient.getParcelShops(carrier, country, limit, accessToken);

        // Step 3: Persist the fetched parcel shops into MongoDB
        parcelShopRepository.saveAll(parcelShops);

        return parcelShops;
    }
}
