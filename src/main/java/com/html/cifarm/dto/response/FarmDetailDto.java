package com.html.cifarm.dto.response;

import com.html.cifarm.domain.Farm;
import com.html.cifarm.dto.type.FarmAmenities;
import com.html.cifarm.dto.type.FarmStatus;

import java.time.LocalDateTime;
import java.util.List;

public record FarmDetailDto(
        Long id,
        String farmText,
        Integer totalArea,
        FarmStatus status,
        List<FarmAmenities> farmAmenities,
        Integer slotCount,
        Integer recruitmentCount,
        LocalDateTime recruitmentStartDate,
        LocalDateTime recruitmentEndDate,
        String farmImgUrl,
        LocalDateTime createdAt,
        List<FarmSlotReadDto> farmSlots
        ) {
}
