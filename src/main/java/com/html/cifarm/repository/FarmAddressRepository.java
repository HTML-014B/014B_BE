package com.html.cifarm.repository;

import com.html.cifarm.domain.FarmAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FarmAddressRepository extends JpaRepository <FarmAddress, Long> {

    Optional<FarmAddress> findByFarmId(Long FarmId);
}
