package com.example.ELFONZENEW.Service;
import com.example.ELFONZENEW.Entity.LockedAccounts;
import com.example.ELFONZENEW.Entity.Admin;
import com.example.ELFONZENEW.Respository.LockedAccountsRespository;
import com.example.ELFONZENEW.Respository.SuperAdminRespository;
import com.example.ELFONZENEW.Respository.AdminRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class AdminServices {

    private Map<String, Integer> loginAttempts = new HashMap<>();
    private String  OTP;

    @Autowired
    private AdminRespository adminRespository;
    @Autowired
    private LockedAccountsRespository lockedAccountsRespository;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SuperAdminRespository superAdminRespository;


    public String addRegistration(Admin admin) {
        if (!isValidName(admin.getAdminName())) {
            return "Invalid name format. Name should contain only alphabetic characters.";
        }

        if (!isValidPassword(admin.getPassword())) {
            return "Invalid password format. Password should contain one small alphabet, one big alphabet, one special character, one digit.";
        }

        if (!isValidEmail(admin.getEmail())) {
            return "Invalid email format.";
        }
        admin.setAuthorized(false);
        adminRespository.save(admin);
        return "admin has been added to the DB";
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

    public String login(String Email,String Password) {

        Optional<Admin> optionaladmin = Optional.ofNullable(adminRespository.findAdminByEmail(Email));
        LockedAccounts lockedAccounts = new LockedAccounts();
        Admin admin = optionaladmin.get();
        if (!admin.getEmail().equals(Email) || !admin.getPassword().equals(Password)) {
            int attempts = loginAttempts.getOrDefault(Email, 0) + 1;
            loginAttempts.put(Email, attempts);
            if (attempts >= 5) {
                lockedAccounts.setEmail(admin.getEmail());
                lockedAccounts.setActive(false);
                lockedAccounts.setAdmin(admin);
                lockedAccountsRespository.save(lockedAccounts);
                return "Invalid email or password. Account locked. Please go to Account Lockout Policy.";
            }
            return "Invalid email or password. Please try again.";
        }

        // Reset login attempts on successful login
        return "Login successful.";
    }

    public String sendOTP(String Email)
    {
       OTP = generateOTP();
       LockedAccounts lockedAccounts= new LockedAccounts();
       lockedAccounts=lockedAccountsRespository.findAdminByEmail(Email);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String body = "Hi "+ lockedAccounts.getAdmin().getAdminName()+" !" +
                "Your 6 Digit otp have been registered. You can verify in next 60 secondst."+"Your OTP: "+OTP;

        mailMessage.setFrom("thelostzoro977@gmail.com");
        mailMessage.setTo(lockedAccounts.getEmail());
        mailMessage.setSubject("OTP VERIFICATION !!");
        mailMessage.setText(body);

        mailSender.send(mailMessage);
        return "otp have been registered";
    }
    public String VerifyOTP(String otp,String Email)
    {
        LockedAccounts lockedAccounts= new LockedAccounts();
        lockedAccounts=lockedAccountsRespository.findAdminByEmail(Email);

        if (OTP != null && OTP.equals(otp)) {
            String password= lockedAccounts.getAdmin().getPassword();
            lockedAccountsRespository.delete(lockedAccounts);

            return "OTP verified successfully."+"Your Password:"+ password;
        } else {
            // OTP didn't match or expired
            return "Invalid OTP.";
        }
    }
    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate a random number between 100000 and 999999
        return String.valueOf(otp);
    }

    public   String updateInformation(String adminName, Admin admin)
    {
        Optional<Admin> optionaladmin = Optional.ofNullable(adminRespository.findAdminByAdminName(adminName));
        Admin admin1 = optionaladmin.get();
        if( admin1.isAuthorized() )
        {
            admin1.setAdminName(admin.getAdminName());
            admin1.setEmail(admin.getEmail());
            admin1.setOrganization(admin.getOrganization());
            admin1.setPassword(admin.getPassword());
            adminRespository.save(admin1);
        }
        else {
            return "your account is still Inactive to update";
        }
        return "Updated Successfully";
    }

}