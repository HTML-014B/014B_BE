package com.html.cifarm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalculatePriceResponseDto {
    private int totalPrice;
    private String message;
}
