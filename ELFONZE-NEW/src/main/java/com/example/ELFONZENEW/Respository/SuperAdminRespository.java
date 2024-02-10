package com.example.ELFONZENEW.Respository;


import com.example.ELFONZENEW.Entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface SuperAdminRespository extends JpaRepository<SuperAdmin,Integer> {
    SuperAdmin findSuperAdminBySuperAdminName(String Name);
}
