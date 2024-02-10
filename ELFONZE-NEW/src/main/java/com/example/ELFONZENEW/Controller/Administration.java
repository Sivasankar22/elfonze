package com.example.ELFONZENEW.Controller;


import com.example.ELFONZENEW.Entity.Admin;
import com.example.ELFONZENEW.Entity.SuperAdmin;

import com.example.ELFONZENEW.Service.SuperAdminServices;
import com.example.ELFONZENEW.Service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("SuperAdmin")
public class Administration {

    @Autowired
    AdminServices adminServices;

    @Autowired
    SuperAdminServices superAdminServices;

    @PostMapping("/AddAdmin")
    public ResponseEntity<String> addUser(@RequestBody Admin admin){

        String result = superAdminServices.addadmin(admin);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    @PostMapping("/AdminRegistration")
    public ResponseEntity<String> addAdmin(@RequestBody SuperAdmin superAdmin){

        String result = superAdminServices.addSuperAdmin(superAdmin);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    @PutMapping ("/Approval")
    public ResponseEntity<String> Approval(@RequestParam("AdminName") String AdminName,@RequestParam("SuperAdminName") String SuperAdminName){

        String result = superAdminServices.ApprovalOfadmin(AdminName,SuperAdminName);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    @PutMapping ("/DeactiveAdmin")
    public ResponseEntity<String> DeactiveAdmin(@RequestParam("AdminName") String AdminName,@RequestParam("SuperAdminName") String SuperAdminName){

        String result = superAdminServices.DeactivingAdmin(AdminName,SuperAdminName);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    @DeleteMapping("/DeletedAdmin")
    public ResponseEntity<String> DeletedAdmin(@RequestParam("AdminName") String AdminName,@RequestParam("SuperAdminName") String SuperAdminName){

        String result = superAdminServices.DeleteAdmin(AdminName,SuperAdminName);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }



}
