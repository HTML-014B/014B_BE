package com.html.cifarm.service;

import com.html.cifarm.domain.FarmSlot;
import com.html.cifarm.dto.response.FarmSlotsResponseDto;
import com.html.cifarm.repository.FarmSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmSlotService {

    private final FarmSlotRepository farmSlotRepository;

    public FarmSlotsResponseDto getAvailableSlots(Long farmId){
        List<FarmSlot> slots = farmSlotRepository.findByFarmId(farmId);
        long availableCount=slots.stream().filter(FarmSlot::getIsAvailable).count();
        long totalCount = slots.size();

        return new FarmSlotsResponseDto(availableCount, totalCount, slots);
    }
}
