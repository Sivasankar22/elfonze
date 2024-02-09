package com.example.ELFONZENEW.Respository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ELFONZENEW.Entity.User;

@Repository
public interface UserRespository extends JpaRepository<User,Integer> {
}
