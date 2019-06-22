package com.globomatics.bikes.repo;

import com.globomatics.bikes.models.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike, Long> {
}
