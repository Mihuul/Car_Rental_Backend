package com.kodilla.car_rental.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VinBodyDto {

    @JsonProperty("Make")
    private String manufacturer;

    @JsonProperty("Model")
    private String model;

    @JsonProperty("ModelYear")
    private String productYear;

    @JsonProperty("VehicleType")
    private String vehicleType;
}
