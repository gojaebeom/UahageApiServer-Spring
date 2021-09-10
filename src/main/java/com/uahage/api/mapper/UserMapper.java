package com.uahage.api.mapper;

import com.uahage.api.dto.UserShowResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserShowResponse findOneById(Long id);
}
