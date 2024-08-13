package com.html.cifarm.controller;

import com.html.cifarm.dto.global.ResponseDto;
import com.html.cifarm.dto.request.FarmAddressRequestDto;
import com.html.cifarm.dto.response.FarmAddressResponseDto;
import com.html.cifarm.service.FarmAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "FarmAddress", description = "농장 주소 관련 API")
@RequestMapping("/api/v1")
public class FarmAddressController {

    private final FarmAddressService farmAddressService;

    @PostMapping("/farm-address/{farmId}")
    @Operation(summary = "농장 주소 입력", description = "농장의 경도, 위도, 주소를 저장합니다")
    public ResponseDto<FarmAddressResponseDto> createFarmAddress(
            @PathVariable Long farmId, @RequestBody FarmAddressRequestDto farmAddressRequestDto
    ){
        FarmAddressResponseDto farmAddressResponseDto=farmAddressService.saveFarmAddress(farmId, farmAddressRequestDto);

        return ResponseDto.ok(farmAddressResponseDto);
    }

    @GetMapping("/{farmId}")
    public ResponseDto<?> getFarmAddress(@PathVariable Long farmId){
        return ResponseDto.ok(farmAddressService.getFarmAddress(farmId));
    }
}
