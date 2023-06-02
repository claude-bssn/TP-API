package com.api.tp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Entity
@Table(name = "cuve")
public class Tank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "masse")
    @NotNull(message = "Weight can't be null")
    @Range(min = 0)
    private Double weight;

    @Column(name = "contenance")
    @NotNull(message = "capacity can't be null")
    @Range(min = 0)
    private Double capacity;

    @Column(name = "numeroSerie")
    @NotNull(message = "Serial number can't be null")
    @NotBlank(message = "Serial number  can't be blank")
    private String serialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Residence residence;

    @OneToMany(mappedBy="tank", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Data> data;

    public Tank() {
    }

    public Tank(@NotNull(message = "Weight can't be null") Double weight, @NotNull(message = "capacity can't be null") Double capacity, @NotNull(message = "Serial number can't be null") String serialNumber) {
        this.weight = weight;
        this.capacity = capacity;
        this.serialNumber = serialNumber;
    }

    public Residence getResidence() {
        return residence;
    }

    public List<Data> getData() {
        return data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }
}

