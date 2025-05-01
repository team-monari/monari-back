package com.monari.monariback.location.repository;

import com.monari.monariback.location.entity.GeneralLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralLocationRepository extends JpaRepository<GeneralLocation, Integer> {

}
