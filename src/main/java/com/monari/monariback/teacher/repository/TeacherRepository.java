package com.monari.monariback.teacher.repository;

import com.monari.monariback.teacher.domain.Teacher;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query("""
        select t from Teacher t
        WHERE t.socialId = :socialId
        """)
    Optional<Teacher> findBySocialId(@Param("socialId") String socialId);
}
