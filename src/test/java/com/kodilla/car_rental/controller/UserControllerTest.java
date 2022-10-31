package com.kodilla.car_rental.controller;

import com.kodilla.car_rental.domain.dto.UserDto;
import com.kodilla.car_rental.facades.UserFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringJUnitWebConfig
@WebMvcTest(UserController.class)
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFacade userFacade;

    @Test
    void shouldFetchAllUsers() throws Exception {
        //Given
        UserDto userDto = createSampleUser();
        List<UserDto> userDtoList = Collections.singletonList(userDto);
        when(userFacade.getAllUsers()).thenReturn(userDtoList);
        //When & Then
        mockMvc.perform(get("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test")))
                .andExpect(jsonPath("$[0].phoneNumber", is(1234)));
    }

    @Test
    void shouldFetchUserById() throws Exception {
        //Given
        UserDto userDto = createSampleUser();
        when(userFacade.getUserById(1L)).thenReturn(userDto);
        //When & Then
        mockMvc.perform(get("/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .param("id", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test")));
    }

    @Test
    void shouldFetchUserByEmail() throws Exception {
        //Given
        UserDto userDto = createSampleUser();
        when(userFacade.getUserByMail("testmail@gmail.com")).thenReturn(userDto);
        //When & Then
        mockMvc.perform(get("/v1/users/mail/testmail@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .param("email", "testmail@gmail.com"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.mail", is("testmail@gmail.com")));
    }

    @Test
    void shouldFetchUserByPhoneNumber() throws Exception {
        //Given
        UserDto userDto = createSampleUser();
        when(userFacade.getUserByPhoneNumber(1234)).thenReturn(userDto);
        //When & Then
        mockMvc.perform(get("/v1/users/phone/1234")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .param("phoneNumber", String.valueOf(1234)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.phoneNumber", is(1234)));
    }

    @Test
    void shouldDeleteUser() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(delete("/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .param("id", "1"))
                .andExpect(status().is(200));
    }

    private UserDto createSampleUser() {
        return UserDto.builder()
                .id(1l)
                .name("Test")
                .surname("Testowski")
                .password("password")
                .mail("testmail@gmail.com")
                .phoneNumber(1234)
                .build();
    }
}