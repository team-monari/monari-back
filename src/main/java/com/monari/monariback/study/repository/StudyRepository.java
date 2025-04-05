package com.monari.monariback.study.repository;

import com.monari.monariback.study.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Integer> {

}
