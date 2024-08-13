package com.html.cifarm.controller;

import com.html.cifarm.domain.Farm;
import com.html.cifarm.domain.FarmAddress;
import com.html.cifarm.dto.global.ResponseDto;
import com.html.cifarm.dto.request.FarmAddressRequestDto;
import com.html.cifarm.dto.response.FarmAddressReadDto;
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
    public ResponseDto<FarmAddressReadDto> getFarmAddress(@PathVariable Long farmId) {
        FarmAddress farmAddress=farmAddressService.getFarmAddressByFarmId(farmId);
        FarmAddressReadDto farmAddressReadDto = new FarmAddressReadDto(farmId, farmAddress.getLongitude(), farmAddress.getLatitude(), farmAddress.getRegion2depthName());
        return ResponseDto.ok(farmAddressReadDto);
    }
}
