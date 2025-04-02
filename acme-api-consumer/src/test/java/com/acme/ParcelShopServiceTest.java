package com.acme;

import com.acme.client.ApiClient;
import com.acme.model.LoginResponse;
import com.acme.model.ParcelShop;
import com.acme.repository.ParcelShopRepository;
import com.acme.service.ParcelShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyByte;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ParcelShopServiceTest {
    @Mock
    private ApiClient apiClient;

    @Mock
    private ParcelShopRepository parcelShopRepository;

    @InjectMocks
    private ParcelShopService parcelShopService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetParcelShops() {
        String accessToken = "mockedAccessToken";
        LoginResponse loginResponse = LoginResponse.builder()
                .accessToken(accessToken)
                .build();

        var mockParcelShop1 = ParcelShop.builder().id("1").name("PS-1").build();
        var mockParcelShop2 = ParcelShop.builder().id("2").name("PS-2").build();

        var expectedParcelShops = List.of(mockParcelShop1, mockParcelShop2);

        // when
        when(apiClient.login()).thenReturn(loginResponse);

        when(apiClient.getParcelShops("CORREOS", "ES", 10, accessToken))
                .thenReturn(expectedParcelShops);

        var result = parcelShopService.performParcelOperation("CORREOS", "ES", 10);

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(apiClient, times(1)).login();
        verify(apiClient, times(1)).getParcelShops("CORREOS", "ES", 10, accessToken);
    }

    @Test
    public void shouldHandleLoginFailure() {
        String errorMessage = "Invalid credentials";

        // when
        when(apiClient.login()).thenThrow(new RuntimeException(errorMessage));

        assertThrows(RuntimeException.class, () -> parcelShopService.performParcelOperation("CORREOS", "ES", 10));

        verify(apiClient, times(1)).login();
        verify(apiClient, times(0)).getParcelShops("CORREOS", "ES", 10, "");
    }
}
