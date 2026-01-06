package com.divya.PlacementService.repo;

import com.divya.PlacementService.entity.ApplicationRound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRoundRepository extends JpaRepository<ApplicationRound,Long> {
    List<ApplicationRound> findByApplicationId(Long applicationId);
}