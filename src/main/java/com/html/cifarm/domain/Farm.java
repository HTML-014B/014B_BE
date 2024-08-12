package com.html.cifarm.domain;

import com.html.cifarm.annotation.Date;
import com.html.cifarm.dto.type.FarmAmenities;
import com.html.cifarm.dto.type.FarmStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="farm_id")
    private Long id;

    @Column(name="farm_text")
    private String farmText;

    @Column(name="total_area", nullable=false)
    private Integer totalArea;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FarmStatus status;

    @Column(name="farm_amenities")
    @Enumerated(EnumType.STRING)
    private FarmAmenities farmAmenities;

    @Column(name="recruitment_count")
    private Integer recruitmentCount;

    @Column(name="recruitment_start_date")
    private LocalDateTime recruitmentStartDate;

    @Column(name="recruitment_end_date")
    private LocalDateTime recruitmentEndDate;

    @Column(name="farm_img_url")
    private String farmImgUrl;

    @CreatedDate
    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;

    @Builder
    public Farm(String farmText, Integer totalArea, FarmStatus status,
                FarmAmenities farmAmenities, Integer recruitmentCount, LocalDateTime recruitmentEndDate, LocalDateTime recruitmentStartDate, String farmImgUrl){
        this.farmText = farmText;
        this.totalArea = totalArea;
        this.status = status;
        this.farmAmenities = farmAmenities;
        this.recruitmentCount = recruitmentCount;
        this.recruitmentStartDate = recruitmentStartDate;
        this.recruitmentEndDate = recruitmentEndDate;
        this.farmImgUrl = farmImgUrl;
        this.createdAt = LocalDateTime.now();
    }
}

