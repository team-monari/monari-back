package com.monari.monariback.student.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monari.monariback.student.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	@Query("""
			select s from Student s
			WHERE s.socialId = :socialId
			""")
	Optional<Student> findBySocialId(@Param("socialId") String socialId);

	boolean existsByPublicId(UUID publicId);

	@Query("""
			select s from Student s
			WHERE s.publicId = :publicId
			""")
	Optional<Student> findByPublicId(@Param("publicId") UUID publicId);
}
