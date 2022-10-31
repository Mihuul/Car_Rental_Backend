package com.kodilla.car_rental.controller;

import com.google.gson.Gson;
import com.kodilla.car_rental.domain.Status;
import com.kodilla.car_rental.domain.dto.CarDto;
import com.kodilla.car_rental.facades.CarFacade;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarFacade carFacade;

    @Test
    void shouldFetchAllCars() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getAllCars()).thenReturn(carDtoList);
        //When & Then
        mockMvc.perform(get("/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    void shouldFetchCarById() throws Exception {
        //Given
        CarDto carDto = createSampleCar();
        when(carFacade.getCarById(1L)).thenReturn(carDto);
        //When & Then
        mockMvc.perform(get("/v1/cars/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .param("id", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.brand", is("Ford")));
    }

    @Test
    void shouldFetchAllCarsByBrand() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getCarsByBrand("Ford")).thenReturn(carDtoList);
        //When & Then
        mockMvc.perform(get("/v1/cars/brand/Ford")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("brand", "Ford"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].brand", is("Ford")));
    }

    @Test
    void shouldFetchAllByProductionYear() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getCarsByProductionYear(2022)).thenReturn(carDtoList);
        //When & Then
        mockMvc.perform(get("/v1/cars/year/2022")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("productionYear", String.valueOf(2022)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].productionYear", is(2022)));
    }

    @Test
    void shouldFetchAllCarsByMileage() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getCarsByMileage(1000)).thenReturn(carDtoList);
        //When & Then
        mockMvc.perform(get("/v1/cars/mileage/1000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("mileage", String.valueOf(1000)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].mileage", is(1000)));
    }

    @Test
    void shouldFetchAllCarsByFuel() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getCarsByFuelType("Diesel")).thenReturn(carDtoList);
        //When & Then
        mockMvc.perform(get("/v1/cars/fuel/Diesel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("fuel", "Diesel"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].fuel", is("Diesel")));
    }

    @Test
    void shouldFetchAllByDailyCost() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getCarsByDailyCost(new BigDecimal("499.99"))).thenReturn(carDtoList);
        //When & Then
        mockMvc.perform(get("/v1/cars/cost/499.99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("cost", String.valueOf(499.99)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].dailyCost", is(499.99)));
    }

    @Test
    void shouldCreateCar() throws Exception {
        //Given
        CarDto carDto = createSampleCar();
        when(carFacade.saveCar(ArgumentMatchers.any(CarDto.class))).thenReturn(carDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);
        //When & Then
        mockMvc.perform(post("/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.vin", is("1234")))
                .andExpect(jsonPath("$.brand", is("Ford")));
    }

    @Test
    void shouldUpdateCar() throws Exception {
        //Given
        CarDto carDto = createSampleCar();
        when(carFacade.saveCar(ArgumentMatchers.any(CarDto.class))).thenReturn(carDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);
        //When & Then
        mockMvc.perform(put("/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.brand", is("Ford")))
                .andExpect(jsonPath("$.model", is("Focus")));
    }


    private CarDto createSampleCar() {
        return CarDto.builder()
                .id(1L)
                .brand("Ford")
                .model("Focus")
                .fuel("Diesel")
                .productionYear(2022)
                .mileage(1000)
                .dailyCost(new BigDecimal("499.99"))
                .vin("1234")
                .status(Status.AVAILABLE)
                .build();
    }

    private List<CarDto> createSampleCarList() {
        CarDto carDto = createSampleCar();
        return Collections.singletonList(carDto);
    }
}