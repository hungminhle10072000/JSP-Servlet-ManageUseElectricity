package com.hdh.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class FormUse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nameForm;

    @Column(nullable = false)
    private Double unitPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "formUse", cascade = CascadeType.ALL)
    private List<Contract> listContracts;
}
