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

    @ManyToOne
    @JoinColumn(name = "id_notebook", nullable = false)
    private NoteBook noteBook;

}
