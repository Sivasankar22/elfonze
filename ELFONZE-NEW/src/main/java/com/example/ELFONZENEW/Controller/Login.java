package com.example.ELFONZENEW.Controller;


import com.example.ELFONZENEW.Service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ELFONZENEW.Entity.Admin;

@RestController
@RequestMapping("Login")
public class Login {


    @Autowired
    AdminServices adminServices;
    @PostMapping("/Registration")
    public ResponseEntity<String> addAuthor(@RequestBody Admin admin){

        String result = adminServices.addRegistration(admin);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    @PostMapping("/SendOTP")
    public ResponseEntity<String> SendOTP(@RequestParam("Email") String Email){

        String result = adminServices.sendOTP(Email);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    @PostMapping("/VerifyOTP")
    public ResponseEntity<String>  VerifyOTP(@RequestParam("OTP") String OTP,@RequestParam("Email") String Email){

        String result = adminServices.VerifyOTP(OTP, Email);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    @GetMapping("/Login")
    public ResponseEntity<String> addAuthor(@RequestParam("Email") String Email,@RequestParam("Password") String Password){

        String result = adminServices.login(Email,Password);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    @PutMapping("/UpdateInformation")
    public  ResponseEntity<String> updateInformation(@RequestParam("AdminName") String  AdminName,@RequestBody Admin admin)
    {
        String result = adminServices.updateInformation(AdminName,admin);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    
}
