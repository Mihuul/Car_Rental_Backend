package com.kodilla.car_rental.mapper;

import com.kodilla.car_rental.domain.Rental;
import com.kodilla.car_rental.domain.dto.RentalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalMapper {

    public RentalDto mapToRentalDto(final Rental rental) {
        return RentalDto.builder()
                .id(rental.getId())
                .rentedFrom(rental.getRentedFrom())
                .rentedUntil(rental.getRentedUntil())
                .carId(rental.getCar().getId())
                .userId(rental.getUser().getId())
                .build();
    }

    public List<RentalDto> mapToRentalDtoList(final List<Rental> rentalList) {
        return rentalList.stream()
                .map(this::mapToRentalDto)
                .collect(Collectors.toList());
    }
}
