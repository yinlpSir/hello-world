package com.hngy.lms.repository;

import com.hngy.lms.entity.Role;
import com.hngy.lms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
//    @Query(value = "select u.username,u.password from User u where u.username=?1")

    /**
     * 根据 username and role 来查找一个人
     * @param username
     * @param role
     * @return
     */
    public User readByUsernameAndRole(String username, Role role);

    // Notice : lazy loading problem

    //u.id,u.username,u.password,u.role,u.number
//    @Query(value = "select u.* from User u where u.username=?1")
    public Optional<User> findByUsername(String username);
//    public User findByUsername(String username);

    public User findById(long id);
}
