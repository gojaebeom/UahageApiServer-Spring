package com.uahage.api.repository;

import com.uahage.api.domain.User;
import com.uahage.api.dto.ResShowUserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    ResShowUserDto findOneById(Long userId);
    User findByEmail(String email);
    Short countByNickname(String nickname);
    void save(User user);
    User findById(Long id);
    void edit(User user);
    void destroyById(Long id);
    Short countByEmail(String email);

}
