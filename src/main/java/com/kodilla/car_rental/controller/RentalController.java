package com.kodilla.car_rental.controller;

import com.kodilla.car_rental.domain.dto.FullRentalDto;
import com.kodilla.car_rental.domain.dto.RentalDto;
import com.kodilla.car_rental.exception.car_exceptions.CarNotFoundException;
import com.kodilla.car_rental.exception.rental_exceptions.RentalNotFoundException;
import com.kodilla.car_rental.exception.user_exceptions.UserNotFoundException;
import com.kodilla.car_rental.facades.RentalFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/rental")
@RequiredArgsConstructor
public class RentalController {

    private final RentalFacade rentalFacade;

    @GetMapping
    public List<FullRentalDto> getAllRentals() {
        return rentalFacade.getAllRentals();
    }

    @GetMapping("/{id}")
    public FullRentalDto getRentalById(@PathVariable Long id) throws RentalNotFoundException {
        return rentalFacade.getRentalById(id);
    }

    @PostMapping
    public FullRentalDto createRental(@RequestBody RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException {
        return rentalFacade.createRental(rentalDto);
    }

    @PutMapping
    public FullRentalDto updateRental(@RequestBody RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException, RentalNotFoundException {
        return rentalFacade.updateRental(rentalDto);
    }

    @DeleteMapping("/{id}")
    public void closeRental(@PathVariable Long id) throws RentalNotFoundException {
        rentalFacade.closeRental(id);
    }
}
