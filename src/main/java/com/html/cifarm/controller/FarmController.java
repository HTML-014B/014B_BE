package com.html.cifarm.controller;

import com.html.cifarm.annotation.UserId;
import com.html.cifarm.dto.global.ResponseDto;
import com.html.cifarm.dto.request.FarmCreateRequestDto;
import com.html.cifarm.dto.response.FarmCreateResponseDto;
import com.html.cifarm.service.FarmService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Farm", description = "농장 관련 API")
@RequestMapping("/api/v1/farm")
public class FarmController {

    private final FarmService farmService;

    @PostMapping("")
    public ResponseDto<?> createFarm(@UserId Long userId, @RequestBody FarmCreateRequestDto farmCreateRequestDto) {

        FarmCreateResponseDto farmCreateResponseDto = farmService.createFarm(userId, farmCreateRequestDto);

        return ResponseDto.ok(farmCreateResponseDto);
    }

    @GetMapping("/{farmId}")
    public ResponseDto<?> getFarmList(@PathVariable Long farmId) {
        return ResponseDto.ok(farmService.getFarmById(farmId));
    }
}
