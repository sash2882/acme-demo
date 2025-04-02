package com.acme.client;

import com.acme.AcmeApiConsumerProperties;
import com.acme.model.LoginRequest;
import com.acme.model.LoginResponse;
import com.acme.model.ParcelShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ApiClient {
    @Autowired
    AcmeApiConsumerProperties properties;
    @Autowired
    private RestTemplate restTemplate;

    public LoginResponse login() {
        String loginUrl = properties.getBaseUrl() + properties.getLoginUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String username = properties.getLogin().getUsername();
        String password = properties.getLogin().getPassword();
        String clientId = properties.getLogin().getClientId();

        LoginRequest loginRequest = LoginRequest.builder()
                .username(username)
                .password(password)
                .clientId(clientId)
                .omitClaims(true)
                .build();

        HttpEntity<LoginRequest> entity = new HttpEntity<>(loginRequest, headers);
        ResponseEntity<LoginResponse> response = restTemplate.exchange(
                loginUrl, HttpMethod.POST, entity, LoginResponse.class);
        return response.getBody();
    }

    public List<ParcelShop> getParcelShops(String carrier, String country, int limit, String accessToken) {
        String parcelShopsUrl = properties.getBaseUrl() + properties.getParcelShopUrl();
        String url = String.format("%s?carrier=%s&country=%s&limit=%d", parcelShopsUrl, carrier, country, limit);

        HttpHeaders headers = new HttpHeaders();
        // Set the Authorization header to Bearer token
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("ClientId", properties.getLogin().getClientId());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ParcelShop[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, ParcelShop[].class);

        return Arrays.asList(response.getBody());
    }

}