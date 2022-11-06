package com.kodilla.car_rental.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullRentalDto {

    private Long id;
    private LocalDate rentedFrom;
    private LocalDate rentedUntil;
    private BigDecimal cost;
    private Long carId;
    private String carBrand;
    private String carModel;
    private String userName;
    private String userSurname;
    private String userMail;
    private int userPhoneNumber;
}
