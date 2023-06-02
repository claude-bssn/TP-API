package com.api.tp.repositories;

import com.api.tp.models.Residence;
import com.api.tp.models.Tank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TankRepository extends CrudRepository<Tank, Long> {
   List<Tank> findByResidence(Residence residence);
}
