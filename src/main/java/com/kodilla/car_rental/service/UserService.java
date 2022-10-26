package com.kodilla.car_rental.service;

import com.kodilla.car_rental.domain.User;
import com.kodilla.car_rental.domain.dto.UserDto;
import com.kodilla.car_rental.exception.UserExceptions.UserAlreadyExistsException;
import com.kodilla.car_rental.exception.UserExceptions.UserNotFoundException;
import com.kodilla.car_rental.mapper.UserMapper;
import com.kodilla.car_rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User getUser(final Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(final UserDto userDto) {
        return userRepository.save(userMapper.mapToUser(userDto));
    }

    public UserDto getUserById(Long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    public UserDto getUserByPhoneNumber(int phoneNumber) throws UserNotFoundException {
        return userMapper.mapToUserDto(userRepository.findByPhoneNumber(phoneNumber).orElseThrow(UserNotFoundException::new));
    }

    public UserDto getUserByMail(String mail) throws UserNotFoundException {
        return userMapper.mapToUserDto(userRepository.findByMail(mail).orElseThrow(UserNotFoundException::new));
    }
}
