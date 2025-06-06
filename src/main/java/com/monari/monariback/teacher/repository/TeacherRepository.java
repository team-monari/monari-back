package com.monari.monariback.teacher.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monari.monariback.teacher.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
	@Query("""
			select t from Teacher t
			WHERE t.socialId = :socialId
			""")
	Optional<Teacher> findBySocialId(@Param("socialId") String socialId);

	boolean existsByPublicId(UUID publicId);

	@Query("""
			select t from Teacher t
			WHERE t.publicId = :publicId
			""")
	Optional<Teacher> findByPublicId(@Param("publicId") UUID publicId);
}
