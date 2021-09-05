package com.uahage.api.repository;

import com.uahage.api.domain.UserDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDetailRepository {
    void save(UserDetail userDetail);
    void edit(UserDetail userDetail);
}
