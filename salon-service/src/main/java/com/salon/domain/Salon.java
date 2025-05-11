package com.salon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "salon")
public class Salon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false)
    private String name ;

    @ElementCollection // this will create a seprate table for this field
    private List<String> images ;

    @Column(nullable = false)
    private String address ;

    @Column(nullable = false)
    private String phoneNumber ;

    @Column(nullable = false)
    private String city ;

    @Column(nullable = false)
    private Long ownerId ;

    @Column(nullable = false)
    private LocalTime openTime ;

    @Column(nullable = false)
    private LocalTime closeTime ;
}
