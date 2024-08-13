package com.html.cifarm.dto.response;
import com.html.cifarm.domain.FarmAddress;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Builder
@Schema(description = "농장 주소 입력 응답 DTO")
public record FarmAddressResponseDto (
        Long id,
        double latitude,
        double longitude,
        String region2depthName,
        LocalDateTime createdAt) {

    public static FarmAddressResponseDto fromEntity(FarmAddress farmAddress) {
        return FarmAddressResponseDto.builder()
                .id(farmAddress.getId())
                .latitude(farmAddress.getLatitude())
                .longitude(farmAddress.getLongitude())
                .region2depthName(farmAddress.getRegion2depthName())
                .createdAt(farmAddress.getCreatedAt())
                .build();
    }
}
