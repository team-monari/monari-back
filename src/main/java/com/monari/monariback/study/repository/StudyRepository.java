package com.monari.monariback.study.repository;

import com.monari.monariback.study.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRepository extends JpaRepository<Study, Integer> {

    @Query("select s " +
            "from Study s " +
            "join fetch s.location"
    )
    List<Study> findAllWithLocation();
}
