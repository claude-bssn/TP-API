package com.api.tp.controllers;

import com.api.tp.models.Residence;
import com.api.tp.models.User;
import com.api.tp.repositories.ResidenceRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/residences")
public class ResidenceController {
    @Autowired
    private ResidenceRepository residenceRepository;

    @GetMapping(value = "/{id}")
    Residence getOne(@PathVariable(name = "id", required = false) Residence residence) {
        if (residence == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User not found"
            );
        }
        return residence;
    }

    @GetMapping(value = "/user={id}")
    List<Residence> findAllByUser(@PathVariable(name = "id", required = false) User user) {
        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User not found"
            );
        }
        List<Residence> residences =  residenceRepository.findByUser(user);
        return residences;
    }

    @PostMapping(value = "/user={id}") // http://localhost:8090/residences
    public ResponseEntity<Residence> saveResidence(@PathVariable(name = "id", required = false) User user, @Valid @RequestBody Residence residence ) {
        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User not found"
            );
        }
        residence.setUser(user);
        residenceRepository.save(residence);
        return new ResponseEntity<>(residence, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Residence> update(@PathVariable(name = "id",required = false) Residence residence, @Valid @RequestBody Residence residenceUpdate, BindingResult bindingResult) {

        if (residence == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User not found"
            );
        }
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, bindingResult.toString()
            );
        }
        residenceUpdate.setId(residence.getId());
        residenceUpdate.setUser(residence.getUser());
        residenceRepository.save(residenceUpdate);
        return new ResponseEntity<>(residenceUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id",required = false) Residence residence) {
        if (residence == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user introuvable" );
        }
        residenceRepository.delete(residence);
        return ResponseEntity.ok("{message: deleted_ok}");
    }
}
