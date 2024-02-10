package com.example.ELFONZENEW.Respository;

import com.example.ELFONZENEW.Entity.LockedAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LockedAccountsRespository extends JpaRepository<LockedAccounts,Integer> {
    LockedAccounts findAdminByEmail(String Email);
}
