package com.hdh.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class ElectricMeter_NoteBook {

    @EmbeddedId
    ElectricMeter_NoteBook_Key id;

    private Double indexElectric;


    @ManyToOne
    @MapsId("electric_id")
    @JoinColumn(name = "electric_id")
    private ElectricMeter electricMeter;

    @ManyToOne
    @MapsId("notebook_id")
    @JoinColumn(name = "notebook_id")
    private NoteBook noteBook;
}
