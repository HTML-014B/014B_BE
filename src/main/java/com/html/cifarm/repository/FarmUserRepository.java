package com.html.cifarm.repository;

import com.html.cifarm.domain.FarmUser;
import com.html.cifarm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FarmUserRepository extends JpaRepository<FarmUser, Long> {
    List<FarmUser> findByUser(User user);
}
