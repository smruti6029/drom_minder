package com.dromminder.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dromminder.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String name);

//	@Query(value = "select * from user where phone=?1 or email=?2", nativeQuery = true)
//	Optional<User> findAllByPhoneNoEmail(String phoneNo, String email);

	@Query(value = "SELECT * FROM user WHERE (phone = ?1 OR email = ?2) AND is_active = true", nativeQuery = true)
	Optional<User> findAllByPhoneNoEmail(String phoneNo, String email);

	List<User> findAll(Specification<User> specification);

	List<User> findByIdIn(Set<Long> allUserId);

	@Query(value = "SELECT * FROM user WHERE college_id = :collegeId AND role = :role and is_active = true", nativeQuery = true)
	List<User> findByCollegeIdAndRole(@Param("collegeId") Integer collegeId, @Param("role") String role);

}