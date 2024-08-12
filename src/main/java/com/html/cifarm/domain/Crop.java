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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "crop")
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crop_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="farm_user_id", nullable = false)
    private FarmUser farmUser;

    @Column(name = "crop_name", nullable = false)
    private String cropName;

    @Column(name = "watring_start_date")
    private LocalDateTime wateringStartDate;

    @Column(name = "watering_end_date")
    private LocalDateTime wateringEndDate;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "crops_img_url")
    private String cropsImgUrl;

    @Builder
    public Crop(FarmUser farmUser, LocalDateTime wateringStartDate,
                LocalDateTime wateringEndDate, String CropName, String cropsImgUrl) {
        this.farmUser = farmUser;
        this.cropName = CropName;
        this.cropsImgUrl = cropsImgUrl;
        this.wateringStartDate = wateringStartDate;
        this.wateringEndDate = wateringEndDate;
        this.createdAt = LocalDateTime.now();
    }
}
