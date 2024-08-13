package com.html.cifarm.service;

import com.html.cifarm.domain.*;
import com.html.cifarm.dto.request.FarmCreateRequestDto;
import com.html.cifarm.dto.response.FarmCreateResponseDto;
import com.html.cifarm.dto.response.FarmSlotReadDto;
import com.html.cifarm.exception.CommonException;
import com.html.cifarm.exception.ErrorCode;
import com.html.cifarm.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FarmService {
    private final FarmRepository farmRepository;
    private final UserRepository userRepository;
    private final FarmUserRepository farmUserRepository;
    private final FarmAddressRepository farmAddressRepository;

    @Transactional
    public FarmCreateResponseDto createFarm(Long userId, FarmCreateRequestDto requestDto) {

        // farm 엔티티 생성 -> 저장
        Farm farm = Farm.builder()
                .farmText(requestDto.farmText())
                .totalArea(requestDto.totalArea())
                .status(requestDto.status())
                .farmAmenities(requestDto.farmAmenities())  // 수정된 부분: List로 처리
                .recruitmentCount(requestDto.recruitmentCount())
                .recruitmentStartDate(requestDto.recruitmentStartDate())
                .recruitmentEndDate(requestDto.recruitmentEndDate())
                .slotCount(requestDto.slotCount())
                .dayPrice(requestDto.dayPrice())
                .build();

        Farm savedFarm=farmRepository.save(farm);

        // slotCount 개수에 따라 FarmSlot 생성
        farm.addFarmSlots(requestDto.slotCount());

        // User 조회 -> UserTeam 생성 및 연관관계 설정
        User user=userRepository.findById(userId)
                .orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_USER));

        FarmUser farmUser = FarmUser.builder()
                .user(user)
                .farm(farm)
                .build();
        farmUserRepository.save(farmUser);

        return FarmCreateResponseDto.fromEntity(savedFarm);
    }

    public Farm getFarmById(Long id) {
        return farmRepository.findById(id)
                .orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_FARM));
    }

    public List<FarmSlotReadDto> getFarmSlotsByFarmId(Long farmId) {
        Farm farm = getFarmById(farmId);
        return farm.getFarmSlots().stream()
                .map(slot -> new FarmSlotReadDto(slot.getSlotId(), slot.getSlotNumber(), slot.getIsAvailable()))
                .collect(Collectors.toList());
    }


    public FarmAddress getFarmAddressByFarmId(Long farmId) {
        return farmAddressRepository.findByFarmId(farmId)
                .orElseThrow(() -> new EntityNotFoundException("FarmAddress not found for farmId: " + farmId));
    }

    }

