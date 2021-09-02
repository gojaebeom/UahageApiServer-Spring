package com.uahage.api.repository;

import com.uahage.api.domain.UserImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserImageRepository {

    List<UserImage> findAllByUserId(Long userId);
    void deleteById(Long id);
    void saveAll(List<UserImage> userImages);
}
