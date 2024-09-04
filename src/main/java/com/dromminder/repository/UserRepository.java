package com.dromminder.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dromminder.dto.PaginationRequestDto;
import com.dromminder.enums.Role;
import com.dromminder.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String name);

//	@Query(value = "select * from user where phone=?1 or email=?2", nativeQuery = true)
//	Optional<User> findAllByPhoneNoEmail(String phoneNo, String email);

	@Query(value = "SELECT * FROM user WHERE (phone = ?1 OR email = ?2) AND is_active = true", nativeQuery = true)
	Optional<User> findAllByPhoneNoEmail(String phoneNo, String email);

//	List<User> findAll(Specification<User> specification);

	List<User> findByIdIn(Set<Long> allUserId);

	@Query(value = "SELECT * FROM user WHERE college_id = :collegeId AND role = :role and is_active = true", nativeQuery = true)
	List<User> findByCollegeIdAndRole(@Param("collegeId") Integer collegeId, @Param("role") String role);

	@Query(value = "SELECT COUNT(*) FROM user WHERE college_id = :collegeId AND role = :role AND is_active = true", nativeQuery = true)
	Long countUserByRoleAndIsActiveTrueWhereCollegeId(@Param("collegeId") Integer collegeId,
			@Param("role") String role);

	Page<User> findAll(Specification<User> parentData, Pageable pageable);

	default Page<User> findAll(PaginationRequestDto paginatedRequest, Pageable pageable) throws Exception {
		try {
			return findAll(search(paginatedRequest), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error occurred while fetching data.");

		}
	}

	static Specification<User> search(PaginationRequestDto paginatedRequest) {
		return (root, cq, cb) -> {
			Predicate p1 = cb.conjunction();

			  if (paginatedRequest.getRole() != null) {
		            Role role = Role.valueOf(paginatedRequest.getRole()); 
		            p1 = cb.and(p1, cb.equal(root.get("role"), role));
		        }
			if (paginatedRequest.getCollegeId() != null) {
				p1 = cb.and(p1, cb.equal(root.get("collegeId"), paginatedRequest.getCollegeId()));
			}

			cq.orderBy(cb.desc(root.get("id")));
			return p1;

		};

	}

}