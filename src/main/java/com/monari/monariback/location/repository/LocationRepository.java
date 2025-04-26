package com.monari.monariback.location.repository;

import com.monari.monariback.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    boolean existsByServiceId(String serviceId);

}
