package com.example.TimeTracker.repo;

import com.example.TimeTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
    boolean existsByUserName(String userName);
    boolean existsByEmailAndPassword(String email, String password);
    //User findByEmailAndPassword(String email, String password); // Custom query to find user by email and password
}
