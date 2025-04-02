package com.acme;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "acme-api-consumer")
@Data
@NoArgsConstructor
public class AcmeApiConsumerProperties {
    private String baseUrl;
    private String loginUrl;
    private String parcelShopUrl;

    private Login login;

    @Data
    @NoArgsConstructor
    public static class Login {
        private String username;
        private String password;
        private String clientId;
    }
}
