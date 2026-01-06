package com.divya.PlacementService.entity;

import jakarta.persistence.*;

@Entity
public class RoundResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long applicationId;
    private Long roundId;

    @Enumerated(EnumType.STRING)
    private RoundStatus status;
}

