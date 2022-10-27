package com.kodilla.car_rental.service;

import com.kodilla.car_rental.domain.Car;
import com.kodilla.car_rental.exception.car_exceptions.CarNotFoundException;
import com.kodilla.car_rental.mapper.CarMapper;
import com.kodilla.car_rental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car getCarById(final Long id) throws CarNotFoundException {
        return carRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void deleteCar(final Long id) {
        carRepository.deleteById(id);
    }

    public Car saveCar(final Car car) {
        return carRepository.save(car);
    }

    public List<Car> getCarByBrand(final String brand) throws CarNotFoundException {
        return carRepository.findAllByBrand(brand);
    }

    public List<Car> getCarsByProductionYear(final int year) {
        return carRepository.findAllByProductionYear(year);
    }

    public List<Car> getCarsByMileage(final int mileage) {
        return carRepository.findAllByMileage(mileage);
    }

    public List<Car> getCarsByFuelType(final String fuel) {
        return carRepository.findAllByFuel(fuel);
    }

    public List<Car> getCarsByDailyCost(final double cost) {
        return carRepository.findAllByDailyCost(cost);
    }
}
