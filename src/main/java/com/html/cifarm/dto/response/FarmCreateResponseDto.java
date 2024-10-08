package com.html.cifarm.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.html.cifarm.domain.Farm;
import com.html.cifarm.domain.FarmSlot;
import com.html.cifarm.dto.type.FarmAmenities;
import com.html.cifarm.dto.type.FarmStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Schema(description = "농장 등록 시 응답 dto")
public record FarmCreateResponseDto (
        Long id,
        String farmText,
        Integer totalArea,
        FarmStatus farmStatus,
        List<FarmAmenities> farmAmenities,
        Integer recruitmentCount,
        LocalDateTime recruitmentStartDate,
        LocalDateTime recruitmentEndDate,
        String farmImgUrl,
        LocalDateTime createdAt,
        Integer slotCount,
        Integer dayPrice
){
    public static FarmCreateResponseDto fromEntity(Farm farm) {

        return FarmCreateResponseDto.builder()
                .id(farm.getId())
                .farmText(farm.getFarmText())
                .totalArea(farm.getTotalArea())
                .farmStatus(farm.getStatus())
                .farmAmenities(farm.getFarmAmenities())
                .recruitmentCount(farm.getRecruitmentCount())
                .recruitmentStartDate(farm.getRecruitmentStartDate())
                .recruitmentEndDate(farm.getRecruitmentEndDate())
                .farmImgUrl(farm.getFarmImgUrl())
                .createdAt(farm.getCreatedAt())
                .slotCount(farm.getSlotCount())
                .dayPrice(farm.getDayPrice())
                .build();
    }
}
