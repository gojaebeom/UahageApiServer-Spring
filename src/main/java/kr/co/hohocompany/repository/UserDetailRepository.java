package kr.co.hohocompany.repository;

import kr.co.hohocompany.domain.user.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

}
