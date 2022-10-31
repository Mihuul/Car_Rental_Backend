package com.kodilla.car_rental.service;

import com.kodilla.car_rental.config.AdminConfig;
import com.kodilla.car_rental.domain.Status;
import com.kodilla.car_rental.repository.CarRepository;
import com.kodilla.car_rental.repository.RentalRepository;
import com.kodilla.car_rental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDailyMailMessageService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;
    private final AdminConfig adminConfig;

    public String emailBodyCreate() {
        long userRepositorySize = userRepository.count();
        long carRentedSize = carRepository.countAllByStatus(Status.RENTED);
        long carAvailableSize = carRepository.countAllByStatus(Status.AVAILABLE);
        long rentalRepositorySize = rentalRepository.count();

        return ("\n Dear Administrator." + adminConfig.getAdminName() +
                "\n\t Below there are daily statistics considering your page: \n" +
                "\n\t Current number of registered users: " + userRepositorySize +
                "\n\t Current number of rented cars: " + carRentedSize +
                "\n\t Current number of available cars: " + carAvailableSize +
                "\n\t Current number of all rentals: " + rentalRepositorySize + "\n" +
                "\n Have a nice day!" +
                "\n //Car Rental service//");
    }
}
