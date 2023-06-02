package com.api.tp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Entity
public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nbPersonne")
    @NotNull(message = "Amount of people can't be null")
    @Range(min = 0)
    private Integer nbPeople;

    @Column(name = "type")
    @NotNull(message = "Type of people can't be null")
    @NotBlank(message = "Type of people can't be blank")
    private String type;

    @Column(name = "princial")
    @NotNull(message = "Main of people can't be null")
    private Boolean main;

    @Column(name = "adresse")
    @NotNull(message = "Address of people can't be null")
    @NotBlank(message = "Address of people can't be blank")
    private String address;

    @Column(name = "codePostal")
    @NotNull(message = "Zip code of people can't be null")
    @NotBlank(message = "Zip code of people can't be blank")
    private String zipCode;

    @Nullable()
    private Double lat;

    @Nullable()
    private Double lon;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy="residence", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Weather> weathers;

    @OneToMany(mappedBy="residence", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Tank> tanks;

    public Residence() {
    }

    public Residence(@NotNull(message = "Amount of people can't be null") Integer nbPeople, @NotNull(message = "Type of people can't be null") String type, @NotNull(message = "Main of people can't be null") Boolean main, @NotNull(message = "Address of people can't be null") String address, @NotNull(message = "Zip code of people can't be null") String zipCode, @Nullable Double lat, @Nullable Double lon) {
        this.nbPeople = nbPeople;
        this.type = type;
        this.main = main;
        this.address = address;
        this.zipCode = zipCode;
        this.lat = lat;
        this.lon = lon;
    }

    public User getUser() {
        return user;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNbPeople() {
        return nbPeople;
    }

    public void setNbPeople(Integer nbPeople) {
        this.nbPeople = nbPeople;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Nullable
    public Double getLat() {
        return lat;
    }

    public void setLat(@Nullable Double lat) {
        this.lat = lat;
    }

    @Nullable
    public Double getLon() {
        return lon;
    }

    public void setLon(@Nullable Double lon) {
        this.lon = lon;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
