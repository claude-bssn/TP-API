package com.api.tp.controllers;

import com.api.tp.models.Data;
import com.api.tp.models.Tank;
import com.api.tp.repositories.DataRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/data")
public class DataController {
    @Autowired
    private DataRepository dataRepository;

    @PostMapping(value = "/tank={id}") // http://localhost:8090/residences
    public ResponseEntity<Data> saveData(@PathVariable(name = "id", required = false) Tank tank, @Valid @RequestBody Data data ) {
        if (tank == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "Tank not found"
            );
        }
        data.setCreationDate(LocalDateTime.now());
        data.setTank(tank);
        dataRepository.save(data);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    Data getOne(@PathVariable(name = "id", required = false) Data data) {
        if (data == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User not found"
            );
        }
        return data;
    }

    @GetMapping(value = "/tank={id}")
    List<Data> findAllByTank(@PathVariable(name = "id", required = false) Tank tank) {
        if (tank == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User not found"
            );
        }
        List<Data> data = dataRepository.findByTank(tank);
        return data;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Data> update(@PathVariable(name = "id",required = false) Data data, @Valid @RequestBody Data dataUpdate, BindingResult bindingResult) {

        if (data == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User not found"
            );
        }
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, bindingResult.toString()
            );
        }
        dataUpdate.setId(data.getId());
        dataUpdate.setCreationDate(data.getCreationDate());
        dataUpdate.setTank(data.getTank());
        dataRepository.save(dataUpdate);
        return new ResponseEntity<>(dataUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteData(@PathVariable(name = "id",required = false) Data data) {
        if (data == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user introuvable" );
        }
        dataRepository.delete(data);
        return ResponseEntity.ok("{message: deleted_ok}");
    }

}
