package com.kodilla.car_rental.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CARS")
public class Car {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "VIN")
    private String vin;

    @NotNull
    @Column(name = "BRAND")
    private String brand;

    @NotNull
    @Column(name = "MODEL")
    private String model;

    @NotNull
    @Column(name = "PRODUCTION_YEAR")
    private int productionYear;

    @NotNull
    @Column(name = "MILEAGE")
    private int mileage;

    @NotNull
    @Column(name = "FUEL")
    private String fuel;

    @NotNull
    @Column(name = "DAILY_COST")
    private BigDecimal dailyCost;

    @NotNull
    @Column(name = "CAR_STATUS")
    private Status status;

    @OneToMany(targetEntity = Rental.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "car")
    private List<Rental> rentalList = new ArrayList<>();
}
