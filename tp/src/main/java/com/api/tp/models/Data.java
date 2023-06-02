package com.api.tp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Entity
@Table(name = "donnee")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "masse")
    @NotNull(message = "Weight can't be null")
    @Range(min = 0)
    private Double weight;

    @Column(name = "dateCreation")
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Tank tank;

    public Data() {
    }

    public Data(@NotNull(message = "Weight can't be null") Double weight, @NotNull(message = "Creation date can't be null") LocalDateTime creationDate) {
        this.weight = weight;
        this.creationDate = creationDate;
    }

    public Tank getTank() {
        return tank;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }
}
