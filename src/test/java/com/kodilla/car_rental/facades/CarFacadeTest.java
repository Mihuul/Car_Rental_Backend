package com.kodilla.car_rental.facades;

import com.kodilla.car_rental.domain.Car;
import com.kodilla.car_rental.domain.Status;
import com.kodilla.car_rental.domain.dto.CarDto;
import com.kodilla.car_rental.exception.car_exceptions.CarNotFoundException;
import com.kodilla.car_rental.mapper.CarMapper;
import com.kodilla.car_rental.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class CarFacadeTest {

    @InjectMocks
    private CarFacade carFacade;

    @Mock
    private CarService carService;

    @Mock
    private CarMapper carMapper;

    @Test
    void shouldGetAllCars() {
        //Given
        List<Car> carList = initCarList();
        List<CarDto> carDtoList = initCarDtoList();

        when(carService.getAllCars()).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getAllCars();

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getStatus(), carDtoList.get(0).getStatus());
        });
    }

    @Test
    void shouldGetCarById() throws CarNotFoundException {
        //Given
        Car car = initCar();
        CarDto carDto = initCarDto();

        when(carService.getCarById(1L)).thenReturn(car);
        when(carMapper.mapToCarDto(car)).thenReturn(carDto);

        //When
        CarDto result = carFacade.getCarById(1L);

        //Then
        assertEquals(carDto.getId(), result.getId());
        assertEquals(carDto.getBrand(), result.getBrand());
    }

    @Test
    void shouldGetCarsByBrand() throws CarNotFoundException {
        //Given
        List<Car> carList = initCarList();
        List<CarDto> carDtoList = initCarDtoList();

        when(carService.getCarsByBrand("Ford")).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByBrand("Ford");

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getBrand(), carDtoList.get(0).getBrand());
        });
    }

    @Test
    void shouldGetCarsByFuelType() throws CarNotFoundException {
        //Given
        List<Car> carList = initCarList();
        List<CarDto> carDtoList = initCarDtoList();

        when(carService.getCarsByFuelType("Diesel")).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByFuelType("Diesel");

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getFuel(), carDtoList.get(0).getFuel());
        });
    }

    @Test
    void shouldGetCarsByMileage() throws CarNotFoundException {
        //Given
        List<Car> carList = initCarList();
        List<CarDto> carDtoList = initCarDtoList();

        when(carService.getCarsByMileage(260000)).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByMileage(260000);

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getMileage(), carDtoList.get(0).getMileage());
        });
    }

    @Test
    void shouldGetCarByDailyCost() throws CarNotFoundException {
        //Given
        List<Car> carList = initCarList();
        List<CarDto> carDtoList = initCarDtoList();

        when(carService.getCarsByDailyCost(new BigDecimal(32))).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByDailyCost(new BigDecimal(32));

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getDailyCost(), carDtoList.get(0).getDailyCost());
        });
    }

    @Test
    void shouldSaveCar() {
        //Given
        Car car = initCar();
        CarDto carDto = initCarDto();

        when(carMapper.mapToCar(any())).thenReturn(car);
        when(carMapper.mapToCarDto(any())).thenReturn(carDto);

        //When
        CarDto savedCar = carFacade.saveCar(carDto);

        //Then
        assertEquals(carDto.getId(), savedCar.getId());
        assertEquals(carDto.getBrand(), savedCar.getBrand());
        assertEquals(carDto.getDailyCost(), savedCar.getDailyCost());
    }

    @Test
    void shouldDeleteCar() {
        //Given
        //When
        carFacade.deleteCar(2L);

        //Then
        verify(carService, times(1)).deleteCar(2L);
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

    private List<Car> initCarList() {
        Car car = initCar();
        return Collections.singletonList(car);
    }

    private List<CarDto> initCarDtoList() {
        CarDto carDto = initCarDto();
        return Collections.singletonList(carDto);
    }
}