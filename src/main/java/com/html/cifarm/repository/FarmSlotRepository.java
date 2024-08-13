package com.html.cifarm.repository;

import com.html.cifarm.domain.FarmSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmSlotRepository extends JpaRepository<FarmSlot, Long> {

    List<FarmSlot> findByFarmId(Long farmId);

    List<FarmSlot> findBySlotNumberIn(List<Integer> slotNumbers);
}
