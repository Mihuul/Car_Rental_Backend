package com.kodilla.car_rental.service;

import com.kodilla.car_rental.client.VinClient;
import com.kodilla.car_rental.domain.dto.VinApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class VinService {

    private final VinClient vinClient;

    public VinApiDto decodeVin(String vin) {
        return vinClient.decodeVin(vin);
    }
}
