package com.example.TimeTracker.service;

import com.example.TimeTracker.entity.User;
import com.example.TimeTracker.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    //@Autowired
    //private BCryptPasswordEncoder BCryptpasswordEncoder;  // Injecting the BCryptPasswordEncoder

    //@Autowired
    //private PasswordEncoder passwordEncoder;


    public User saveDetails(User user) {
        if (!userRepo.existsByEmailAndPassword(user.getEmail(), user.getPassword()))

            return userRepo.save(user);
        else {
            return null;
        }
    }

    public boolean getDetails(User user) {
       //return userRepo.existsByUserName(user.getUserName());
        return userRepo.existsByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    // Example method where you use the passwordEncoder
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        //return passwordEncoder.matches(rawPassword, encodedPassword);  // Compare passwords
        //Could nto get this to work
        return true;
    }

    public User findByEmail(String email){
        return userRepo.findByEmail(email);
    }

}
