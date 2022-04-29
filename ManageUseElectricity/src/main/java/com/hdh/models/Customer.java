package com.hdh.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;

    private String phoneNumber;

}
