package com.example.auiplanet.planet.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PutPlanetRequest {
    private String name;
    private int population;
    private UUID star;
}
