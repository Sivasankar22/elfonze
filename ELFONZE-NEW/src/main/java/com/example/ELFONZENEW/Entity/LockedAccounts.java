package com.example.ELFONZENEW.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LockedAccounts {


    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private Integer id;

    private  String email;
    private  Boolean active;



    @OneToOne
    @JoinColumn
    private Admin admin;
}
