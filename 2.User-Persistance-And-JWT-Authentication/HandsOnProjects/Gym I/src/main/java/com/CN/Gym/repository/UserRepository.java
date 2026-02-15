package com.CN.Gym.repository;

import com.CN.Gym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email = :email")
    Optional<User> getUserByUsername(String email);

    Optional<User> findByEmail(String email);

}
