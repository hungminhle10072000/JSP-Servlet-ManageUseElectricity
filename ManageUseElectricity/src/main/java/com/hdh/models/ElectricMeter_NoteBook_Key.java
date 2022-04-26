package com.hdh.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class ElectricMeter_NoteBook_Key implements Serializable {

    private Long electric_id;

    private Integer notebook_id;
}
