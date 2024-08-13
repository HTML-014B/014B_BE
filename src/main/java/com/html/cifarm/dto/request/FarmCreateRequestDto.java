package com.html.cifarm.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.html.cifarm.dto.type.FarmAmenities;
import com.html.cifarm.dto.type.FarmStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Schema(name = "FarmCreateRequestDto", description = "농장 세부 정보 등록")
public record FarmCreateRequestDto(
        @JsonProperty("farmId") Long farmId,
        @JsonProperty("farmText") String farmText,
        @JsonProperty("totalArea") Integer totalArea,
        @JsonProperty("status") FarmStatus status,
        @JsonProperty("farmAmenities") List<FarmAmenities> farmAmenities,
        @JsonProperty("recruitmentCount") Integer recruitmentCount,
        @JsonProperty("recruitmentStartDate") LocalDateTime recruitmentStartDate,
        @JsonProperty("recruitmentEndDate") LocalDateTime recruitmentEndDate,
        @JsonProperty("slotCount") Integer slotCount,
        @JsonProperty("slotPrice") Integer slotPrice,
        @JsonProperty("dayPrice") Integer dayPrice
        ){
}
