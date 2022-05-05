package com.hdh.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
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
    @JoinColumn(name = "electric_id", nullable = false)
    private ElectricMeter electricMeter;

    public boolean isStatusPaid() {
        return statusPaid;
    }
}
