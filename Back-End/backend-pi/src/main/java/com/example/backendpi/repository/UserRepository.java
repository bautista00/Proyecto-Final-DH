package com.example.backendpi.repository;

import com.example.backendpi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("from User u where u.name =:name")
    User getFirstByName(@Param("name") String name);
}
