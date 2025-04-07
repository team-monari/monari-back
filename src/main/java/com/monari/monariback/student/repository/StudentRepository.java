package com.monari.monariback.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monari.monariback.student.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	@Query("""
			select s from Student s
			WHERE s.socialId = :socialId
			""")
	Optional<Student> findBySocialId(@Param("socialId") String socialId);
}
