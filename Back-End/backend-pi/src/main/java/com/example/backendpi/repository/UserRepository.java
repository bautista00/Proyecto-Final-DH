package com.example.backendpi.repository;

import com.example.backendpi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByTokenEmail(String token);
    Optional<User> findByName(String name);

    @Query("from User u where u.email =:email")
    User getFirstByEmail(@Param("email") String email);
}
