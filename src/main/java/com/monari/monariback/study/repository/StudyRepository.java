package com.monari.monariback.study.repository;

import com.monari.monariback.study.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Integer> {

}
