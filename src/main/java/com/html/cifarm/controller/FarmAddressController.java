package com.html.cifarm.controller;

import com.html.cifarm.domain.Farm;
import com.html.cifarm.dto.global.ResponseDto;
import com.html.cifarm.dto.request.FarmAddressRequestDto;
import com.html.cifarm.dto.response.FarmAddressResponseDto;
import com.html.cifarm.dto.response.FarmDetailDto;
import com.html.cifarm.dto.response.FarmSlotReadDto;
import com.html.cifarm.service.FarmAddressService;
import com.html.cifarm.service.FarmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "FarmAddress", description = "농장 주소 관련 API")
@RequestMapping("/api/v1/farm-address")
public class FarmAddressController {

    private final FarmAddressService farmAddressService;
    private final FarmService farmService;

    @PostMapping("/{farmId}")
    @Operation(summary = "농장 주소 입력", description = "농장의 경도, 위도, 주소를 저장합니다")
    public ResponseDto<FarmAddressResponseDto> createFarmAddress(
            @PathVariable Long farmId, @RequestBody FarmAddressRequestDto farmAddressRequestDto
    ){
        FarmAddressResponseDto farmAddressResponseDto=farmAddressService.saveFarmAddress(farmId, farmAddressRequestDto);

        return ResponseDto.ok(farmAddressResponseDto);
    }

    @GetMapping("/{farmId}")
    public ResponseDto<?> getFarmDetails(@PathVariable Long farmId) {
        Farm farm = farmService.getFarmById(farmId);
        List<FarmSlotReadDto> farmSlotDtos = farmService.getFarmSlotsByFarmId(farmId);

        // Farm 객체와 FarmSlotDto 리스트를 포함한 응답 객체 생성
        return ResponseDto.ok(new FarmDetailDto(
                farm.getId(),
                farm.getFarmText(),
                farm.getTotalArea(),
                farm.getStatus(),
                farm.getFarmAmenities(),
                farm.getSlotCount(),
                farm.getRecruitmentCount(),
                farm.getRecruitmentStartDate(),
                farm.getRecruitmentEndDate(),
                farm.getFarmImgUrl(),
                farm.getCreatedAt(),
                farmSlotDtos));
    }
}
