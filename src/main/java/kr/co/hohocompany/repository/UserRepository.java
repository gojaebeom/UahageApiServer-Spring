package kr.co.hohocompany.repository;

import kr.co.hohocompany.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Long countByEmail(String email);
    User findByEmail(String email);
    Long countByNickname(String nickname);
    User findOneById(Long userId);
}
