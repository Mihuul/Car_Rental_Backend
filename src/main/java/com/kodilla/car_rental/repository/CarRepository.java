package com.kodilla.car_rental.repository;

import com.kodilla.car_rental.domain.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {

    @Override
    List<Car> findAll();

    List<Car> findAllByBrand(String brand);

    List<Car> findAllByProductionYear(int productionYear);

    List<Car> findAllByMileage(int mileage);

    List<Car> findAllByFuel(String fuel);

    List<Car> findAllByDailyCost(double cost);
}
