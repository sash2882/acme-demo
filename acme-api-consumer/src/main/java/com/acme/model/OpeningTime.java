package com.acme.model;

import lombok.Data;

@Data
public class OpeningTime {
    private Integer day;
    private String from;
    private String to;
    private String closedDuring;
}
