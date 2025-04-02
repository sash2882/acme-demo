package com.acme.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ParcelShop {
    private String id;
    private String name;
    private String type;
    private Double latitude;
    private Double longitude;
    private String carrier;
    private String addressLine1;
    private String postCode;
    private String city;
    private String country;
    private List<OpeningTime> openingTimes;
    private String carrierData;
}
