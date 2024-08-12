package com.html.cifarm.dto.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FarmAmenities {
    WATER_STATION("WATER_STATION"),
    CONTAINER("CONTAINER"),
    RESTROOM("RESTROOM"),
    FARM_EQUIPMENT("FARM_EQUIPMENT");

    private final String value;

    @Override
    public String toString() {return value;}
}
