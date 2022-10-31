package com.kodilla.car_rental.service;

import com.kodilla.car_rental.client.EmailVerificationClient;
import com.kodilla.car_rental.domain.dto.EmailVerificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationClient emailVerificationClient;

    public EmailVerificationDto verifyEmail(final String email) {
        return emailVerificationClient.verifyEmail(email);
    }
}
