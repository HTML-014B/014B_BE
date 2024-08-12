package com.html.cifarm.dto.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FarmStatus {
    NOT_RECRUITING("NOT RECRUITING"),
    RECRUITING("RECRUITING"),
    RECRUITING_END("RECRUITING_END");

    private final String status;

    @Override
    public String toString() {return status;}
}
