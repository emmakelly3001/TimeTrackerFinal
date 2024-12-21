package com.example.TimeTracker.service;

import com.example.TimeTracker.entity.User;
import com.example.TimeTracker.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


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



}
