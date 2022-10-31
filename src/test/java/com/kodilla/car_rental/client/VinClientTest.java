package com.kodilla.car_rental.client;

import com.kodilla.car_rental.config.VinConfig;
import com.kodilla.car_rental.domain.dto.VinApiDto;
import com.kodilla.car_rental.domain.dto.VinBodyDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VinClientTest {

    @InjectMocks
    private VinClient vinClient;

    @Mock
    private VinConfig vinConfig;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void shouldDecodeVin() throws URISyntaxException {

        when(vinConfig.getVinApiEndpoint()).thenReturn("https://vpic.nhtsa.dot.gov/api/vehicles/decodevinvalues");

        //Given
        VinBodyDto vinBodyDto = VinBodyDto.builder()
                .manufacturer("Ford")
                .model("Focus")
                .productYear("2006")
                .vehicleType("PASSENGER CAR")
                .build();

        List<VinBodyDto> vinBodyDtoList = Collections.singletonList(vinBodyDto);
        VinApiDto vinApiDto = new VinApiDto(vinBodyDtoList);

        URI uri = new URI("https://vpic.nhtsa.dot.gov/api/vehicles/decodevinvalues/WBAVD13596KX00407?format=json");
        when(restTemplate.getForObject(uri, VinApiDto.class)).thenReturn(vinApiDto);

        //When
        VinApiDto result = vinClient.decodeVin("WBAVD13596KX00407");

        //Then
        assertEquals("Ford", result.getVinBodyDtoList().get(0).getManufacturer());
        assertEquals("Focus", result.getVinBodyDtoList().get(0).getModel());

    }

}