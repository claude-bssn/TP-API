package com.api.tp.controllers;

import com.api.tp.models.Residence;
import com.api.tp.models.Weather;
import com.api.tp.repositories.WeatherRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/weathers")
public class WeatherController {
    @Autowired
    private WeatherRepository weatherRepository;

    @PostMapping(value = "/residence={id}")
    public ResponseEntity<Weather> saveData(@PathVariable(name = "id", required = false) Residence residence, @Valid @RequestBody Weather weather ) {
        if (residence == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "Tank not found"
            );
        }
        weather.setDate(LocalDateTime.now());
        weather.setResidence(residence);
        weatherRepository.save(weather);
        return new ResponseEntity<>(weather, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Weather> update(@PathVariable(name = "id",required = false) Weather weather, @Valid @RequestBody Weather weatherUpdate, BindingResult bindingResult) {

        if ( weather == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User not found"
            );
        }
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, bindingResult.toString()
            );
        }
        weatherUpdate.setId(weather.getId());
        weatherUpdate.setDate(LocalDateTime.now());
        weatherUpdate.setResidence(weather.getResidence());
        weatherRepository.save(weatherUpdate);
        return new ResponseEntity<>(weatherUpdate, HttpStatus.OK);
    }

    @GetMapping(value = "/residence={id}")
    public ResponseEntity<List<Weather>> getLastNWeatherForResidence(@PathVariable(name = "id",required = false) Residence residence) {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "date"));
        List<Weather> weatherList = weatherRepository.findByResidenceIdOrderByDateDesc(residence.getId(), pageable);
        return new ResponseEntity<>(weatherList, HttpStatus.OK);
    }

}
