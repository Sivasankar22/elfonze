package com.example.ELFONZENEW.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true,nullable = false)
    private String adminName;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(unique = true,nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private  String organization;


    private boolean authorized;




    @ManyToOne
    @JoinColumn
    private SuperAdmin Superadmin;


}
