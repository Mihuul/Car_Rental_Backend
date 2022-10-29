package com.kodilla.car_rental.service;

import com.kodilla.car_rental.domain.Car;
import com.kodilla.car_rental.domain.Rental;
import com.kodilla.car_rental.domain.Status;
import com.kodilla.car_rental.domain.User;
import com.kodilla.car_rental.domain.dto.RentalDto;
import com.kodilla.car_rental.exception.car_exceptions.CarNotFoundException;
import com.kodilla.car_rental.exception.rental_exceptions.RentalNotFoundException;
import com.kodilla.car_rental.exception.user_exceptions.UserNotFoundException;
import com.kodilla.car_rental.mapper.RentalMapper;
import com.kodilla.car_rental.repository.CarRepository;
import com.kodilla.car_rental.repository.RentalRepository;
import com.kodilla.car_rental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalService {

    private final RentalMapper rentalMapper;
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public Rental getRentalById(final Long id) throws RentalNotFoundException {
        return rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental createRental(final RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException {
        User user = userRepository.findById(rentalDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Car car = carRepository.findById(rentalDto.getCarId()).orElseThrow(CarNotFoundException::new);
        car.setStatus(Status.RENTED);
        carRepository.save(car);

        Rental rental = Rental.builder()
                .rentedFrom(rentalDto.getRentedFrom())
                .rentedUntil(rentalDto.getRentedUntil())
                .user(user)
                .car(car)
                .build();

        return rental;
    }

    public Rental updateRental(RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException, RentalNotFoundException {
        User user = userRepository.findById(rentalDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Car car = carRepository.findById(rentalDto.getCarId()).orElseThrow(CarNotFoundException::new);
        Rental rental = rentalRepository.findById(rentalDto.getId()).orElseThrow(RentalNotFoundException::new);

        rental.setUser(user);
        rental.setCar(car);
        rental.setRentedFrom(rentalDto.getRentedFrom());
        rental.setRentedUntil(rentalDto.getRentedFrom());
        updateDuration(rental);
        updateCost(rental);

        return rental;
    }

    public void updateDuration(Rental rental) {
        if (rental.getRentedUntil().isAfter(rental.getRentedFrom())) {
            rental.setDaysRented(DAYS.between(rental.getRentedFrom(), rental.getRentedUntil()));
        } else {
            rental.setDaysRented(0L);
        }
    }

    public void updateCost(Rental rental) {
        BigDecimal updatedCost = rental.getCar().getDailyCost().multiply(new BigDecimal(rental.getDaysRented()));
        rental.setCost(updatedCost);
    }

    public void closeRental(Long id) throws RentalNotFoundException {
        Rental rental = rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);

        rental.getUser().getRentalList().remove(rental);
        rental.getCar().getRentalList().remove(rental);
        rental.getCar().setStatus(Status.AVAILABLE);

        rentalRepository.deleteById(id);
    }
}
