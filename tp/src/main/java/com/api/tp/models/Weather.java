package com.api.tp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "temps")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temps")
    @NotNull(message = "Weather of people can't be null")
    @NotBlank(message = "Weather of people can't be blank")
    private String weatherData;

    @Column(name = "temperatureMin")
    @NotNull(message = "temperature Minimum can't be null")
    private Double temperatureMin;

    @Column(name = "temperatureMax")
    @NotNull(message = "temperature Maximum  can't be null")
    private Double temperatureMax;

    @Column(name = "date")
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Residence residence;

    public Weather() {
    }

    public Weather(@NotNull(message = "Weather of people can't be null") String weatherData, @NotNull(message = "temperature Minimum can't be null") Double temperatureMin, @NotNull(message = "temperature Maximum  can't be null") Double temperatureMax, @NotNull(message = "Date  can't be null") LocalDateTime date) {
        this.weatherData = weatherData;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.date = date;
    }

    public Residence getResidence() {
        return residence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(String weatherData) {
        this.weatherData = weatherData;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }
}
