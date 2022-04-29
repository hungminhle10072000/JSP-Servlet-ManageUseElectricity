package com.hdh.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Business extends Customer {

    @Column
    private String taxCode;

}
