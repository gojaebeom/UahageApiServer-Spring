package kr.co.hohocompany.uahage.repository.user;

import kr.co.hohocompany.uahage.domain.user.User;
import kr.co.hohocompany.uahage.domain.user.UserDetail;
import kr.co.hohocompany.uahage.domain.user.UserImage;

public interface UserRepository {
    void create(User user, UserDetail userDetail, UserImage userImage) throws Exception;
    void update(User user, UserDetail userDetail, UserImage userImage) throws Exception;
    Boolean validateNickname(String nickname) throws Exception;
}
