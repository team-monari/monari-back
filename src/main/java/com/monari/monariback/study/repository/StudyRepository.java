package com.monari.monariback.study.repository;

import com.monari.monariback.study.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudyRepository extends JpaRepository<Study, Integer>, StudyCustomRepository {

    @Query("""
        SELECT s
        FROM Study s
        JOIN FETCH s.student
        WHERE s.id = :id
    """)
    Optional<Study> findByIdWithStudent(@Param("id") Integer id) ;

    @Query("""
        SELECT s
        FROM Study s
        JOIN FETCH s.student
        LEFT JOIN FETCH s.location
        LEFT JOIN FETCH s.generalLocation
        WHERE s.id = :id
    """)
    Optional<Study> findByIdWithStudentAndLocation(@Param("id") Integer id) ;
}
