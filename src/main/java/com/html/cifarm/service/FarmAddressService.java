package com.html.cifarm.service;

import com.html.cifarm.domain.FarmAddress;
import com.html.cifarm.domain.Farm;
import com.html.cifarm.repository.FarmAddressRepository;
import com.html.cifarm.repository.FarmRepository;
import com.html.cifarm.dto.response.FarmAddressResponseDto;
import com.html.cifarm.dto.request.FarmAddressRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FarmAddressService {

    private final FarmAddressRepository farmAddressRepository;
    private final FarmRepository farmRepository;

    @Transactional
    public FarmAddressResponseDto saveFarmAddress(Long farmId, FarmAddressRequestDto requestDto) {
        Farm farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid farm ID"));

        FarmAddress farmAddress = FarmAddress.builder()
                .farm(farm)
                .latitude(requestDto.latitude())
                .longitude(requestDto.longitude())
                .region2depthName(requestDto.region2depthName())
                .build();

        FarmAddress savedAddress = farmAddressRepository.save(farmAddress);

        return FarmAddressResponseDto.fromEntity(savedAddress);
    }
}
