package com.html.cifarm.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "farm_address")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FarmAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "farm_address_id")
    private Long id;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="farm_id", nullable=false)
    private Farm farm;

    private Float latitude;

    private Float longitude;

    private String region2depthName;

    @CreatedDate
    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;

    @Builder
    public FarmAddress(Farm farm, Float latitude, Float longitude, String region2depthName) {
        this.farm = farm;
        this.latitude = latitude;
        this.longitude = longitude;
        this.region2depthName = region2depthName;
        this.createdAt = LocalDateTime.now();
    }
}

