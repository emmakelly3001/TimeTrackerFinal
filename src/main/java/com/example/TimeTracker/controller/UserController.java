package com.example.TimeTracker.controller;

import com.example.TimeTracker.entity.User;
import com.example.TimeTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public User postDetails(@RequestBody User user) {

        return userService.saveDetails(user);

    }

    //@PostMapping("/userLogin2")
    //public User getDetails2(@RequestBody User user) {
        //User existingUser = userService.getDetails(user);
                //if(existingUser != null){
                    //return existingUser;
                //}else{
                  //  throw new RuntimeException("Invalid email or password");
                //}
            //    return (existingUser);
    //}

    @PostMapping("/userLogin")
    public boolean getDetails(@RequestBody User user) {
        boolean existingUser = userService.getDetails(user);
        return existingUser;
        /*if (existingUser){
            return true;
        }else{
            return false;
        }*/
    }

   // @PostMapping(path = "/login")
    //public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO)
    //{
      //  LoginMessage loginMessage = userService.loginUser(loginDTO);
        //return ResponseEntity.ok(loginMessage);
    //}

}
















/*@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //saving the user in the database
    @PostMapping(path = "/save")
    public String saveUser(@RequestBody UserDTO userDTO)
    {
        String id = userService.addUser(userDTO);
        return id;
    }


}*/
