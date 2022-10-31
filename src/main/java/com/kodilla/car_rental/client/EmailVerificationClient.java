package com.kodilla.car_rental.client;

import com.kodilla.car_rental.config.EmailVerificationConfig;
import com.kodilla.car_rental.domain.dto.EmailVerificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class EmailVerificationClient {

    private final RestTemplate restTemplate;
    private final EmailVerificationConfig emailVerificationConfig;

    private URI getEmailVerificationUri(String email) {
        return UriComponentsBuilder.fromHttpUrl(emailVerificationConfig.getEmailVerificationApiEndpoint())
                .queryParam("apiKey", emailVerificationConfig.getEmailVerificationApiKey())
                .queryParam("emailAddress", email)
                .build().encode().toUri();
    }

    public EmailVerificationDto verifyEmail(String email) {
        URI url = getEmailVerificationUri(email);
        return restTemplate.getForObject(url, EmailVerificationDto.class);
    }
}
