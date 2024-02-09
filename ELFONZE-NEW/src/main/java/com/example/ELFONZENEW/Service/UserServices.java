package com.example.ELFONZENEW.Service;
import com.example.ELFONZENEW.Entity.User;
import com.example.ELFONZENEW.Respository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserServices {


    @Autowired
    private UserRespository userRespository;


    public String addRegistration(User user) {
        if (!isValidName(user.getUserName())) {
            return "Invalid name format. Name should contain only alphabetic characters.";
        }

        if (!isValidPassword(user.getPassword())) {
            return "Invalid password format. Password should contain one small alphabet, one big alphabet, one special character, one digit.";
        }

        if (!isValidEmail(user.getEmail())) {
            return "Invalid email format.";
        }

        userRespository.save(user);
        return "User has been added to the DB";
    }

    private boolean isValidName(String name) {
        return Pattern.matches("^[a-zA-Z]+$", name);
    }

    private boolean isValidPassword(String password) {
        // Password should contain one small alphabet, one big alphabet, one special character, one digit.
        return Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=.*[a-zA-Z0-9@#$%^&+=]).{8,}$", password);
    }

    private boolean isValidEmail(String email) {
        // Email format validation using a simple regex
        return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email);
    }

}