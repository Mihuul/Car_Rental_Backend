package com.kodilla.car_rental.facades;

import com.kodilla.car_rental.domain.dto.FullRentalDto;
import com.kodilla.car_rental.domain.dto.RentalDto;
import com.kodilla.car_rental.exception.car_exceptions.CarNotFoundException;
import com.kodilla.car_rental.exception.rental_exceptions.RentalNotFoundException;
import com.kodilla.car_rental.exception.user_exceptions.UserNotFoundException;
import com.kodilla.car_rental.mapper.RentalMapper;
import com.kodilla.car_rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RentalFacade {

    private final RentalService rentalService;
    private final RentalMapper rentalMapper;

    public FullRentalDto getRentalById(final Long id) throws RentalNotFoundException {
        return rentalMapper.mapToFullRentalDto(rentalService.getRentalById(id));
    }

    public List<FullRentalDto> getAllRentals() {
        return rentalMapper.mapToFullRentalDtoList(rentalService.getAllRentals());
    }

    public FullRentalDto createRental(final RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException {
        return rentalMapper.mapToFullRentalDto(rentalService.createRental(rentalDto));
    }

    public FullRentalDto updateRental(final RentalDto rentalDto) throws UserNotFoundException, RentalNotFoundException, CarNotFoundException {
        return rentalMapper.mapToFullRentalDto(rentalService.updateRental(rentalDto));
    }

    public void closeRental(Long id) throws RentalNotFoundException {
        rentalService.closeRental(id);
    }
}
