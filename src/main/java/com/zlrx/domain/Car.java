package com.zlrx.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Car")
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Basic
    private String manufacturer;

    @Basic
    private String type;

    @Column(unique = true)
    private String licencePlate;

    @Basic
    private String color;

    @Basic
    private Integer capacity;

    @Basic
    private LocalDate dateOfManufacture;

}
