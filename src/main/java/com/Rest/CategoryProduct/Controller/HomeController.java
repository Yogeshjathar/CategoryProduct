package com.Rest.CategoryProduct.Controller;

import com.Rest.CategoryProduct.Entity.AppUser;
import com.Rest.CategoryProduct.Repositories.UserRepositories;
import com.Rest.CategoryProduct.Service.CustomUserDetailService;
import com.Rest.CategoryProduct.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private userService userService;

    @GetMapping("/user")
    public String getUser(){
        System.out.print("Getting User");
        return "User";
    }

    @GetMapping("/currentUserName")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody AppUser user){
        String status = userService.createUser(user);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
