package com.hourlyrecruite.hourlyrecruite.repository;
import com.hourlyrecruite.hourlyrecruite.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}


