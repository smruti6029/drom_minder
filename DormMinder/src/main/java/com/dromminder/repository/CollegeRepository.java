package com.dromminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dromminder.model.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Integer> {
}
