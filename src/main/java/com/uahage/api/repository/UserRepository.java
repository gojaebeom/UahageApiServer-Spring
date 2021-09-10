package com.uahage.api.repository;

import com.uahage.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findByEmail(String email);
    Short countByNickname(String nickname);
    Short countByEmail(String email);
}
