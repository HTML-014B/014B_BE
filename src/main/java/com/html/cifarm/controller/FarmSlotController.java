package com.html.cifarm.controller;

import com.html.cifarm.dto.response.FarmSlotsResponseDto;
import com.html.cifarm.service.FarmSlotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Farm", description = "농장 관련 API")
@RequestMapping("/api/v1/farm-slot")
public class FarmSlotController {

    private final FarmSlotService farmSlotService;

    @GetMapping("/{farmId}")
    @Operation(summary = "농장 구획 조회", description = "총 구획의 개수, 남은 구획의 개수, 구획별 점유 여부를 조회합니다.")
    public FarmSlotsResponseDto getAvailableSlots(@PathVariable Long farmId) {
        return farmSlotService.getAvailableSlots(farmId);
    }

}
