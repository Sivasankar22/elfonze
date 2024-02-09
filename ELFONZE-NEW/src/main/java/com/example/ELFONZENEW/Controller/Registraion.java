package com.example.ELFONZENEW.Controller;


import com.example.ELFONZENEW.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ELFONZENEW.Entity.User;

@RestController
@RequestMapping("Login")
public class Login {


    @Autowired
    UserServices userServices;
    @PostMapping("/Registration")
    public ResponseEntity<String> addAuthor(@RequestBody User user){

        String result = userServices.addRegistration(user);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
