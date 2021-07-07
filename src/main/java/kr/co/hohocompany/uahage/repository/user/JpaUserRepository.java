package kr.co.hohocompany.uahage.repository.user;

import kr.co.hohocompany.uahage.domain.user.User;
import kr.co.hohocompany.uahage.domain.user.UserDetail;
import kr.co.hohocompany.uahage.domain.user.UserImage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
@AllArgsConstructor
public class JpaUserRepository implements UserRepository{

    private final EntityManager entityManager;

    @Override
    public void create(User user, UserDetail userDetail, UserImage userImage) {
        entityManager.persist(user);
        entityManager.persist(userDetail);
    }

    @Override
    public void update(User user, UserDetail userDetail, UserImage userImage) {
        User findUser = entityManager.find(User.class, user.getId());
    }

    @Override
    public Boolean validateNickname(String nickname) {

        User user = entityManager.createQuery(
                "select u from User u " +
                        "where u.nickname =:nickname",
                User.class)
                .setParameter("nickname", nickname)
                .getSingleResult();

        System.out.println(user);

        return ( user == null ) ? true : false;
    }
}
