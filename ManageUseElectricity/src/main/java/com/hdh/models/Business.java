package com.hdh.models;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Data
@OnDelete(action = OnDeleteAction.CASCADE)
public class Business extends Customer {

    @Column
    private String taxCode;

}
