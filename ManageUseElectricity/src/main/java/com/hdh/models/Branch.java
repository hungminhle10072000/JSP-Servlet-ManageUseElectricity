package com.hdh.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nameBranch;

    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch", cascade = CascadeType.ALL)
    private List<Contract> listContracts;

}
