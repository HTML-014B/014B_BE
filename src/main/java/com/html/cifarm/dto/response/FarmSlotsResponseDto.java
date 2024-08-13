package com.html.cifarm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.html.cifarm.domain.FarmSlot;

import java.util.List;

@Getter
@AllArgsConstructor
public class FarmSlotsResponseDto {
    private long availableSlotCount;
    private long totalSlotCount;
    private List<FarmSlot> slots;
}