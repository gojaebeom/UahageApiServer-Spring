package kr.co.hohocompany.repository;

import kr.co.hohocompany.domain.user.UserImage;
import kr.co.hohocompany.repository.mapper.UserImageForUserEdit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
    List<UserImage> findAllByUserId(Long userId);

}
