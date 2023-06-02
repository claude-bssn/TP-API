package com.api.tp.controllers;

import com.api.tp.models.Residence;
import com.api.tp.models.Tank;
import com.api.tp.repositories.TankRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/tanks")
public class TankController {
    @Autowired
    private TankRepository tankRepository;

    @PostMapping(value = "/residence={id}") // http://localhost:8090/residences
    public ResponseEntity<Tank> saveTank(@PathVariable(name = "id", required = false) Residence residence, @Valid @RequestBody Tank tank ) {
        if (residence == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User not found"
            );
        }
        tank.setResidence(residence);
        tankRepository.save(tank);
        return new ResponseEntity<>(tank, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    Tank getOne(@PathVariable(name = "id", required = false) Tank tank) {
        if (tank == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User not found"
            );
        }
        return tank;
    }

    @GetMapping(value = "/residence={id}")
    List<Tank> findAllByResidence(@PathVariable(name = "id", required = false) Residence residence) {
        if (residence == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User not found"
            );
        }
        List<Tank> tanks =  tankRepository.findByResidence(residence);
        return tanks;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Tank> update(@PathVariable(name = "id",required = false) Tank tank, @Valid @RequestBody Tank tankUpdate, BindingResult bindingResult) {

        if (tank == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User not found"
            );
        }
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, bindingResult.toString()
            );
        }
        tankUpdate.setId(tank.getId());
        tankRepository.save(tankUpdate);
        return new ResponseEntity<>(tankUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id",required = false) Tank tank) {
        if (tank == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user introuvable" );
        }
        tankRepository.delete(tank);
        return ResponseEntity.ok("{message: deleted_ok}");
    }
}
