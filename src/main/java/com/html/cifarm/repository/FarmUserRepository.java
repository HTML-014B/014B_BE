package com.html.cifarm.repository;

import com.html.cifarm.domain.FarmUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmUserRepository extends JpaRepository<FarmUser, Long> {
}
