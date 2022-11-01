package com.kodilla.car_rental.mapper;

import com.kodilla.car_rental.domain.User;
import com.kodilla.car_rental.domain.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @InjectMocks
    private UserMapper userMapper;

    @Test
    void mapToUserTest() {
        //Given
        UserDto userDto = initUserDto();
        //When
        User user = userMapper.mapToUser(userDto);
        //Then
        assertEquals("Jan", user.getName());
        assertEquals("Kowalski", user.getSurname());
        assertEquals("password", user.getPassword());
        assertEquals("mail", user.getMail());
        assertEquals(1234, user.getPhoneNumber());
    }

    @Test
    void mapToUserDtoTest() {
        //Given
        User user = initUser();
        //When
        UserDto userDto = userMapper.mapToUserDto(user);
        //Then
        assertEquals("Jan", userDto.getName());
        assertEquals("Kowalski", userDto.getSurname());
        assertEquals("password", userDto.getPassword());
        assertEquals("mail", userDto.getMail());
        assertEquals(1234, userDto.getPhoneNumber());
    }

    @Test
    void mapToUserDtoListTest() {
        //Given
        User user = initUser();
        List<User> userList = new ArrayList<>();
        userList.add(user);
        //When
        List<UserDto> userDtos = userMapper.mapToUserDtoList(userList);
        //Then
        assertEquals(1, userDtos.size());
        assertEquals("Jan", userDtos.get(0).getName());
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

    private UserDto initUserDto() {
        return UserDto.builder()
                .id(1L)
                .name("Jan")
                .surname("Kowalski")
                .password("password")
                .mail("mail")
                .phoneNumber(1234)
                .build();
    }
}