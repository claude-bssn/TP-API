package com.api.tp.repositories;

import com.api.tp.models.Residence;
import com.api.tp.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidenceRepository extends CrudRepository<Residence, Long> {

    List<Residence> findByUser(User user);
}
