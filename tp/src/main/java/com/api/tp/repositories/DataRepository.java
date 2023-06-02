package com.api.tp.repositories;

import com.api.tp.models.Data;
import com.api.tp.models.Tank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends CrudRepository<Data, Long> {
    List<Data> findByTank(Tank tank);
}
