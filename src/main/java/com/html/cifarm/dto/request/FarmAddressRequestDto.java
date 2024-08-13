package com.html.cifarm.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "FarmAddressRequestDto", description = "농장 주소 등록")
public record FarmAddressRequestDto (
        @JsonProperty("latitude") double latitude,
        @JsonProperty("longitude") double longitude,
        @JsonProperty("region2depthName") String region2depthName
){

}
