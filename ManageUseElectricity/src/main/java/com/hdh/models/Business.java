package com.hdh.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Business extends Customer {

    @Column
    private int taxCode;

}
