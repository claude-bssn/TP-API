package com.api.tp.repositories;

import com.api.tp.models.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {
    List<Weather> findByResidenceIdOrderByDateDesc(Long residenceId, Pageable pageable);
}
