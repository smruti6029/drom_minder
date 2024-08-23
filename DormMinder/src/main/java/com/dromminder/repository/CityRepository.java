package com.dromminder.repository;

import com.dromminder.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    @Query(value = "SELECT * FROM city WHERE state_id = :stateId", nativeQuery = true)
    List<City> findByStateId(@Param("stateId") Integer stateId);

   
    List<City> findByName(String name);
}
