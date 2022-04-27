package com.hdh.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalMoney;

    private Date dateFrom;

    private Date dateEnd;

    private boolean statusPaid;

    private Double totalIndex;

    @OneToOne
    @JoinColumn(name = "notebook_id", nullable = false)
    private NoteBook noteBook;

}
