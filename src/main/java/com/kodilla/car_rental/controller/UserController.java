package com.kodilla.car_rental.controller;

import com.kodilla.car_rental.domain.dto.UserDto;
import com.kodilla.car_rental.exception.user_exceptions.UserNotFoundException;
import com.kodilla.car_rental.facades.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userFacade.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) throws UserNotFoundException {
        return userFacade.getUserById(id);
    }

    @GetMapping("/mail/{email}")
    public UserDto getUserByMail(@PathVariable String email) throws UserNotFoundException {
        return userFacade.getUserByMail(email);
    }

    @GetMapping("/phone/{phoneNumber}")
    public UserDto getUserByPhoneNumber(@PathVariable int phoneNumber) throws UserNotFoundException {
        return userFacade.getUserByPhoneNumber(phoneNumber);
    }

    @GetMapping("/exist")
    public Boolean doesUserExist(@RequestParam String email) {
        return userFacade.doesUserWithMailExist(email);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userFacade.saveUser(userDto);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userFacade.saveUser(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userFacade.deleteUser(id);
    }
}
