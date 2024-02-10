package com.example.ELFONZENEW.Respository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ELFONZENEW.Entity.Admin;

@Repository
public interface AdminRespository extends JpaRepository<Admin,Integer> {

    Admin findAdminByEmail(String Email);
    Admin findAdminByAdminName(String Name);
}
