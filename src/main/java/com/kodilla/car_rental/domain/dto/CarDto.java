package com.kodilla.car_rental.domain.dto;

import com.kodilla.car_rental.domain.Status;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private int productionYear;
    private int mileage;
    private String fuel;
    private double dailyCost;
    private Status status;
}
