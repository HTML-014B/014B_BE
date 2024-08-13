package com.html.cifarm.service;

import com.html.cifarm.domain.Farm;
import com.html.cifarm.dto.request.FarmCreateRequestDto;
import com.html.cifarm.dto.response.FarmCreateResponseDto;
import com.html.cifarm.exception.CommonException;
import com.html.cifarm.exception.ErrorCode;
import com.html.cifarm.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FarmService {
    private final FarmRepository farmRepository;

    @Transactional
    public FarmCreateResponseDto createFarm(FarmCreateRequestDto requestDto) {
        Farm farm = Farm.builder()
                .farmText(requestDto.farmText())
                .totalArea(requestDto.totalArea())
                .status(requestDto.status())
                .farmAmenities(requestDto.farmAmenities())  // 수정된 부분: List로 처리
                .recruitmentCount(requestDto.recruitmentCount())
                .recruitmentStartDate(requestDto.recruitmentStartDate())
                .recruitmentEndDate(requestDto.recruitmentEndDate())
                .build();

        Farm savedfarm=farmRepository.save(farm);

        return FarmCreateResponseDto.fromEntity(savedfarm);
    }

    public Farm getFarmById(Long id) {
        return farmRepository.findById(id)
                .orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_FARM));
    }
}

