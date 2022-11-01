package com.kodilla.car_rental.service;

import com.kodilla.car_rental.domain.Car;
import com.kodilla.car_rental.domain.Rental;
import com.kodilla.car_rental.domain.Status;
import com.kodilla.car_rental.domain.User;
import com.kodilla.car_rental.domain.dto.RentalDto;
import com.kodilla.car_rental.exception.car_exceptions.CarNotFoundException;
import com.kodilla.car_rental.exception.rental_exceptions.RentalNotFoundException;
import com.kodilla.car_rental.exception.user_exceptions.UserNotFoundException;
import com.kodilla.car_rental.repository.CarRepository;
import com.kodilla.car_rental.repository.RentalRepository;
import com.kodilla.car_rental.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RentalServiceTest {

    @InjectMocks
    private RentalService rentalService;

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CarRepository carRepository;

    @Test
    void shouldGetAllRentals() {
        //Given
        Rental rental = initRental();
        List<Rental> rentalList = Collections.singletonList(rental);

        when(rentalRepository.findAll()).thenReturn(rentalList);

        //When
        List<Rental> resultList = rentalService.getAllRentals();

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
    }

    @Test
    void shouldGetRentalById() throws RentalNotFoundException {
        //Given
        Rental rental = initRental();

        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));

        //When
        Rental result = rentalService.getRentalById(1L);

        //Then
        assertEquals(result.getId(), result.getId());
    }

    @Test
    void shouldCreateRental() throws UserNotFoundException, CarNotFoundException {
        //Given
        User user = initUser();
        Car car = initCar();
        Rental rental = initRental();
        RentalDto rentalDto = initRentalDto();

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(carRepository.findById(any())).thenReturn(Optional.of(car));
        when(rentalRepository.save(any())).thenReturn(rental);

        //When
        Rental createdRental = rentalService.createRental(rentalDto);

        //Then
        assertEquals(createdRental.getCar().getBrand(), car.getBrand());
        assertEquals(createdRental.getUser().getName(), user.getName());
        assertEquals(createdRental.getRentedFrom(), LocalDate.of(2022, 10, 10));
        assertEquals(createdRental.getRentedUntil(), LocalDate.of(2022, 10, 10));
    }

    @Test
    void shouldUpdateRental() throws UserNotFoundException, CarNotFoundException, RentalNotFoundException {
        //Given
        User user = initUser();
        Car car = initCar();
        Rental rental = initRental();
        RentalDto rentalDto = initRentalDto();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));

        //When
        Rental modifiedRental = rentalService.updateRental(rentalDto);

        //Then
        assertEquals(rentalDto.getUserId(), modifiedRental.getUser().getId());
        assertEquals(rentalDto.getCarId(), modifiedRental.getCar().getId());
        assertEquals(rentalDto.getRentedFrom(), modifiedRental.getRentedFrom());
        assertEquals(rentalDto.getRentedUntil(), modifiedRental.getRentedUntil());
    }


    private User initUser() {
        return User.builder()
                .id(1L)
                .name("Jan")
                .surname("Kowalski")
                .password("password")
                .mail("mail")
                .phoneNumber(1234)
                .build();
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

    private Rental initRental() {
        User user = initUser();
        Car car = initCar();

        return Rental.builder()
                .id(1L)
                .rentedFrom(LocalDate.of(2022, 10, 10))
                .rentedUntil(LocalDate.of(2022, 10, 10))
                .cost(new BigDecimal(100))
                .user(user)
                .car(car)
                .build();
    }

    private RentalDto initRentalDto() {
        return RentalDto.builder()
                .id(1L)
                .rentedFrom(LocalDate.of(2022, 10, 10))
                .rentedUntil(LocalDate.of(2022, 10, 10))
                .carId(1L)
                .userId(1L)
                .build();
    }
}
