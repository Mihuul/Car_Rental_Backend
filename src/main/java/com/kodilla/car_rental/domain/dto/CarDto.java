package com.kodilla.car_rental.domain.dto;

import com.kodilla.car_rental.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private Long id;
    private String vin;
    private String brand;
    private String model;
    private int productionYear;
    private int mileage;
    private String fuel;
    private BigDecimal dailyCost;
    private Status status;
}
