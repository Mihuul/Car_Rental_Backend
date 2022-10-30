package com.kodilla.car_rental.client;

import com.kodilla.car_rental.config.VinConfig;
import com.kodilla.car_rental.domain.dto.VinApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class VinClient {

    private final RestTemplate restTemplate;
    private final VinConfig vinConfig;

    public VinApiDto decodeVin(String vin) {
        URI uri = getVinApiUri(vin);
        return restTemplate.getForObject(uri, VinApiDto.class);
    }

    public URI getVinApiUri(String vin) {
        return UriComponentsBuilder.fromHttpUrl(vinConfig.getVinApiEndpoint() + "/" + vin)
                .queryParam("format", "json")
                .build()
                .encode()
                .toUri();
    }
}
