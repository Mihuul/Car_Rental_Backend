package com.kodilla.car_rental.service;

import com.kodilla.car_rental.domain.Login;
import com.kodilla.car_rental.exception.login_exceptions.LoginNotFoundException;
import com.kodilla.car_rental.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public Boolean isLoginRegistered(String email, String password) {
        return loginRepository.existsByEmailAndPassword(email, password);
    }

    public Login getLoginByEmailAndPassword(String email, String password) throws LoginNotFoundException {
        return loginRepository.findByEmailAndPassword(email, password).orElseThrow(LoginNotFoundException::new);
    }

    public void saveLogin(Login login) {
        loginRepository.save(login);
    }

    public void deleteLogin(Login login) {
        loginRepository.delete(login);
    }
}
