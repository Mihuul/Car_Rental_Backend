package com.kodilla.car_rental.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalDto {
    private Long id;
    private LocalDate rentedFrom;
    private LocalDate rentedUntil;
    private Long carId;
    private Long userId;
}
