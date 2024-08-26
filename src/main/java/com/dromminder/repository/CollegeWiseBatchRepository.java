package com.dromminder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dromminder.model.CollegeWiseBatch;

@Repository
public interface CollegeWiseBatchRepository extends JpaRepository<CollegeWiseBatch, Integer> {

	@Query(value = "SELECT * FROM college_wise_batchh WHERE college_id = :collegeId", nativeQuery = true)
	List<CollegeWiseBatch> findAllByCollegeId(@Param("collegeId") Integer collegeId);

	@Query(value = "SELECT * FROM college_wise_batch WHERE college_id = :collegeId AND name = :name", nativeQuery = true)
	CollegeWiseBatch findByCollegeIdAndName(@Param("collegeId") Integer collegeId, @Param("name") String name);
}
