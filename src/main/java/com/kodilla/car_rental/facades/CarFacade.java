package com.kodilla.car_rental.facades;

import com.kodilla.car_rental.domain.Car;
import com.kodilla.car_rental.domain.dto.CarDto;
import com.kodilla.car_rental.exception.car_exceptions.CarNotFoundException;
import com.kodilla.car_rental.mapper.CarMapper;
import com.kodilla.car_rental.repository.CarRepository;
import com.kodilla.car_rental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CarFacade {

    private final CarService carService;
    private final CarMapper carMapper;

    public CarDto getCarById(final Long id) throws CarNotFoundException {
        return carMapper.mapToCarDto(carService.getCarById(id));
    }

    public List<CarDto> getAllCars() {
        return carMapper.mapToCarDtoList(carService.getAllCars());
    }

    public List<CarDto> getCarsByBrand(final String brand) throws CarNotFoundException {
        return carMapper.mapToCarDtoList(carService.getCarsByBrand(brand));
    }

    public List<CarDto> getCarsByProductionYear(final int year) throws CarNotFoundException {
        return carMapper.mapToCarDtoList(carService.getCarsByProductionYear(year));
    }

    public List<CarDto> getCarsByMileage(final int mileage) throws CarNotFoundException {
        return carMapper.mapToCarDtoList(carService.getCarsByMileage(mileage));
    }

    public List<CarDto> getCarsByFuelType(final String fuel) throws CarNotFoundException {
        return carMapper.mapToCarDtoList(carService.getCarsByFuelType(fuel));
    }

    public List<CarDto> getCarsByDailyCost(final BigDecimal cost) throws CarNotFoundException {
        return carMapper.mapToCarDtoList(carService.getCarsByDailyCost(cost));
    }

    public CarDto saveCar(CarDto carDto) {
        return carMapper.mapToCarDto(carService.saveCar(carMapper.mapToCar(carDto)));
    }

    public void deleteCar(final Long id) {
        carService.deleteCar(id);
    }
}
