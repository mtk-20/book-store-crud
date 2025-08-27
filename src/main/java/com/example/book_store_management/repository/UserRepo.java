package com.example.book_store_management.repository;

import com.example.book_store_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

  //  @Query("select u from User u where u.name = :name")
    Optional<User> findByUserName(String userName);

    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
