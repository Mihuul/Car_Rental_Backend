package com.kodilla.car_rental.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LOGINS")
public class Login {

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

}
