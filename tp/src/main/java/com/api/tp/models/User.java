package com.api.tp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "utilisateur", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    @NotNull(message = "last name value can't be null")
    @NotBlank(message = "last name value can't be blank")
    private String lastName;

    @Column(name = "prenom")
    @NotNull(message = "last name value can't be null")
    @NotBlank(message = "last name value can't be blank")
    private String firstName;

    @Column(name = "email")
    @NotNull(message = "first name value can't be null")
    @NotBlank(message = "first name value can't be blank")
    private String email;

    @Column(name = "mdp")
    @NotNull(message = "password value can't be null")
    @NotBlank(message = "password value can't be blank")
    private String password;

    @Column(name = "telephone")
    @NotNull(message = "phone value can't be null")
    @NotBlank(message = "phone value can't be blank")
    private String phone;

    @Column(name = "portable")
    @NotNull(message = "first name value can't be null")
    @NotBlank(message = "first name value can't be blank")
    private String mobile;

    @Column(name = "actif")
    private Boolean active = true;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Residence> residences;

    public User() {
    }

    public User(@NotNull(message = "last name value can't be null") String lastName, @NotNull(message = "last name value can't be null") String firstName, @NotNull(message = "first name value can't be null") String email, @NotNull(message = "password value can't be null") String password, @NotNull(message = "phone value can't be null") String phone, @NotNull(message = "first name value can't be null") String mobile, Boolean active, List<Residence> residences) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.mobile = mobile;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Residence> getResidences() {
        return residences;
    }

}
