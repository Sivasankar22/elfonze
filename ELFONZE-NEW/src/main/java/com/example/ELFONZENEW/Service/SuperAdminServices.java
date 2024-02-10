package com.example.ELFONZENEW.Service;


import com.example.ELFONZENEW.Entity.AuditRecords;
import com.example.ELFONZENEW.Entity.SuperAdmin;
import com.example.ELFONZENEW.Entity.Admin;
import com.example.ELFONZENEW.Respository.AuditRecordsRespository;
import com.example.ELFONZENEW.Respository.SuperAdminRespository;
import com.example.ELFONZENEW.Respository.AdminRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

import static com.example.ELFONZENEW.Entity.Authorized.Active;

@Service
public class SuperAdminServices {

    @Autowired
    SuperAdminRespository superAdminRespository;

    @Autowired
    AdminRespository adminRespository;
    @Autowired
    AuditRecordsRespository auditRecordsRespository;

    public String addSuperAdmin(SuperAdmin superAdmin) {
        superAdminRespository.save(superAdmin);
        return "superAdmin Registration Sucessfull";
    }




    public String addadmin(Admin admin) {
        if (!isValidName(admin.getAdminName())) {
            return "Invalid name format. Name should contain only alphabetic characters.";
        }

        if (!isValidPassword(admin.getPassword())) {
            return "Invalid password format. Password should contain one small alphabet, one big alphabet, one special character, one digit.";
        }

        if (!isValidEmail(admin.getEmail())) {
            return "Invalid email format.";
        }

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

    public String ApprovalOfadmin(String adminName,String SuperAdminName) {


        Optional<Admin> optionalNewadmin = Optional.ofNullable(adminRespository.findAdminByAdminName(adminName));
        SuperAdmin superAdmin = superAdminRespository.findSuperAdminBySuperAdminName(SuperAdminName);
        Admin admin = optionalNewadmin.get();
        String name= superAdmin.getSuperAdminName();

        admin.setSuperadmin(superAdmin);
        admin.setAuthorized(true);

        adminRespository.save(admin);

        return "admin has been authorized";

    }
    public String DeactivingAdmin(String adminName,String SuperAdminName) {


        Optional<Admin> optionalNewadmin = Optional.ofNullable(adminRespository.findAdminByAdminName(adminName));
        SuperAdmin superAdmin = superAdminRespository.findSuperAdminBySuperAdminName(SuperAdminName);
        Admin admin = optionalNewadmin.get();



        AuditRecords auditRecords = new AuditRecords();
        auditRecords.setAdminName(admin.getAdminName());
        auditRecords.setEmail(admin.getEmail());
        auditRecords.setOrganization(admin.getOrganization());
        auditRecords.setPassword(admin.getPassword());
        admin.setSuperadmin(admin.getSuperadmin());
        admin.setAuthorized(false);

        auditRecordsRespository.save(auditRecords);

        adminRespository.delete(admin);


        return "Admin Account has been Deactived";

    }
    public String DeleteAdmin(String adminName,String SuperAdminName) {


        Optional<Admin> optionalNewadmin = Optional.ofNullable(adminRespository.findAdminByAdminName(adminName));
        SuperAdmin superAdmin = superAdminRespository.findSuperAdminBySuperAdminName(SuperAdminName);
        Admin admin = optionalNewadmin.get();

        adminRespository.delete(admin);


        return "Admin has been Deleted";

    }

}
