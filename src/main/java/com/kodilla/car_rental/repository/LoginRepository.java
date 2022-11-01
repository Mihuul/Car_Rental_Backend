package com.kodilla.car_rental.repository;

import com.kodilla.car_rental.domain.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface LoginRepository extends CrudRepository<Login, Long> {


    Boolean existsByEmailAndPassword(String email, String password);

    Optional<Login> findByEmailAndPassword(String email, String password);
}
