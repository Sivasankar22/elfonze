package com.example.ELFONZENEW.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuperAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private  String superAdminName;




    @OneToMany(mappedBy = "Superadmin",cascade = CascadeType.ALL)
    private List<Admin> adminList = new ArrayList<>();



}
