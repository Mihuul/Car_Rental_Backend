package com.kodilla.car_rental.repository;

import com.kodilla.car_rental.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface RentalRepository extends CrudRepository<Rental, Long> {

    @Override
    List<Rental> findAll();
}
