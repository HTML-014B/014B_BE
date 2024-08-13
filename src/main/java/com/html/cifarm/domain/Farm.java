package com.html.cifarm.domain;

import com.html.cifarm.annotation.Date;
import com.html.cifarm.dto.type.FarmAmenities;
import com.html.cifarm.dto.type.FarmStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

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

    @ElementCollection(targetClass = FarmAmenities.class)
    @CollectionTable(name = "farm_amenities", joinColumns = @JoinColumn(name = "farm_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "amenity")
    private List<FarmAmenities> farmAmenities;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<FarmSlot> farmSlots = new ArrayList<>();

    @Column(name = "slot_count")
    private Integer slotCount;

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
                List<FarmAmenities> farmAmenities, Integer slotCount, Integer recruitmentCount, LocalDateTime recruitmentEndDate, LocalDateTime recruitmentStartDate, String farmImgUrl){
        this.farmText = farmText;
        this.totalArea = totalArea;
        this.status = status;
        this.farmAmenities = farmAmenities;
        this.recruitmentCount = recruitmentCount;
        this.slotCount = slotCount;
        this.recruitmentStartDate = recruitmentStartDate;
        this.recruitmentEndDate = recruitmentEndDate;
        this.farmImgUrl = farmImgUrl;
        this.createdAt = LocalDateTime.now();
    }

    public void addFarmSlots(int count) {
        for (int i = 1; i <= count; i++) {
            FarmSlot farmSlot = FarmSlot.builder()
                    .farm(this)  // 현재 Farm 객체를 설정
                    .isAvailable(true)  // 기본값 설정
                    .slotNumber(i)  // 구획 번호 설정
                    .availableSlotNumber(0)  // 초기값 설정
                    .slotPrice(0)  // 초기값 설정
                    .build();
            this.farmSlots.add(farmSlot);
        }
    }
}

