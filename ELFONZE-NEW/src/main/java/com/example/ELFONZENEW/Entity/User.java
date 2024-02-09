package com.example.ELFONZENEW.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Registration")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true,nullable = false)
    private String userName;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(unique = true,nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private  String organization;

}
