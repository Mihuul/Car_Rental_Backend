package com.kodilla.car_rental.mapper;

import com.kodilla.car_rental.domain.Car;
import com.kodilla.car_rental.domain.Status;
import com.kodilla.car_rental.domain.dto.CarDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarMapperTest {

    @InjectMocks
    private CarMapper carMapper;

    @Test
    void mapToCarTest() {
        //Given
        CarDto carDto = initCarDto();
        //When
        Car car = carMapper.mapToCar(carDto);
        //Then
        assertEquals("123", car.getVin());
        assertEquals("Diesel", car.getFuel());
        assertEquals(Status.AVAILABLE, car.getStatus());
    }

    @Test
    void mapToCarDtoTest() {
        //Given
        Car car = initCar();
        //When
        CarDto carDto = carMapper.mapToCarDto(car);
        //Then
        assertEquals("123", carDto.getVin());
        assertEquals("Diesel", carDto.getFuel());
        assertEquals(Status.AVAILABLE, carDto.getStatus());
    }

    @Test
    void mapToCarDtoList() {
        //Given
        Car car = initCar();
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        //When
        List<CarDto> carDtoList = carMapper.mapToCarDtoList(carList);
        //Then
        assertEquals(1, carDtoList.size());
        assertEquals(2008, carDtoList.get(0).getProductionYear());
    }


    private Car initCar() {
        return Car.builder()
                .id(1L)
                .vin("123")
                .brand("Ford")
                .model("Focus")
                .productionYear(2008)
                .mileage(10000)
                .fuel("Diesel")
                .dailyCost(new BigDecimal("200"))
                .status(Status.AVAILABLE)
                .build();

    }

    private CarDto initCarDto() {
        return CarDto.builder()
                .id(1L)
                .vin("123")
                .brand("Ford")
                .model("Focus")
                .productionYear(2008)
                .mileage(10000)
                .fuel("Diesel")
                .dailyCost(new BigDecimal("200"))
                .status(Status.AVAILABLE)
                .build();
    }

}