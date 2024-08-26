package com.dromminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dromminder.model.State;

public interface StateRepository extends JpaRepository<State, Integer> {
    
}
