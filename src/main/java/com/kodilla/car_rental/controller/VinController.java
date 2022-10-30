package com.kodilla.car_rental.controller;

import com.kodilla.car_rental.domain.dto.VinApiDto;
import com.kodilla.car_rental.service.VinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/vin")
@RequiredArgsConstructor
public class VinController {

    private final VinService vinService;

    @GetMapping("/{vin}")
    public VinApiDto decodeVin(@PathVariable String vin) {
        return vinService.decodeVin(vin);
    }
}
