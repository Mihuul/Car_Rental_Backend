package com.kodilla.car_rental.mapper;

import com.kodilla.car_rental.domain.Car;
import com.kodilla.car_rental.domain.dto.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarMapper {

    public Car mapToCar(final CarDto carDto) {
        return Car.builder()
                .vin(carDto.getVin())
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .productionYear(carDto.getProductionYear())
                .mileage(carDto.getMileage())
                .fuel(carDto.getFuel())
                .dailyCost(carDto.getDailyCost())
                .status(carDto.getStatus())
                .build();
    }

    public CarDto mapToCarDto(final Car car){
        return CarDto.builder()
                .id(car.getId())
                .vin(car.getVin())
                .brand(car.getBrand())
                .model(car.getModel())
                .productionYear(car.getProductionYear())
                .mileage(car.getMileage())
                .fuel(car.getFuel())
                .dailyCost(car.getDailyCost())
                .status(car.getStatus())
                .build();
    }

    public List<CarDto> mapToCarDtoList(final List<Car> carList) {
        return carList.stream()
                .map(this::mapToCarDto)
                .collect(Collectors.toList());
    }
}
