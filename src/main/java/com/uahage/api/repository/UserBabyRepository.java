package com.uahage.api.repository;

import com.uahage.api.domain.user.User;
import com.uahage.api.domain.user.UserBaby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBabyRepository extends JpaRepository<UserBaby, Long> {
    List<UserBaby> findAllByUser(User user);
}
