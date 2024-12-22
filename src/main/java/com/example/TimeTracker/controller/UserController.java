package com.example.TimeTracker.controller;

import com.example.TimeTracker.entity.User;
import com.example.TimeTracker.service.UserService;
import com.example.TimeTracker.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@RestController
public class UserController {

    //adding a password encoder for security
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Autowired
    private UserRepo userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        //validate and save the hashed password
        System.out.println(user.getPassword() + "before");
        user.setPassword(passwordEncoder.encode(user.getPassword()));//rehash on server
        System.out.println(user.getPassword() + "after");

        userRepository.save(user); //save the user
        return ResponseEntity.ok("User Registered Successfully!");

    //insecure code
    //public User postDetails(@RequestBody User user) {

        // return userService.saveDetails(user);

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

        //boolean existingUser = userService.getDetails(user);
        //User userInDatabase = userService.findByEmail(user.getEmail());
        Boolean exists = userService.validatePassword(user.getPassword(), user.getPassword());
        //String test = userService.login(user.getEmail(), user.getPassword());
        System.out.println(exists);
        return exists;
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
