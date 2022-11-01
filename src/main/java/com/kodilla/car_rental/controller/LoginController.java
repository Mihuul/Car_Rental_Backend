package com.kodilla.car_rental.controller;

import com.kodilla.car_rental.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/logins")
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/is_registered")
    public Boolean isLoginRegistered(@RequestParam String email, @RequestParam String password) {
        return loginService.isLoginRegistered(email, password);
    }
}
