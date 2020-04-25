package com.dayup.seckil.repository;

import com.dayup.seckil.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author domn
 * @version 1.0
 * @date 2020/1/12 1:20
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
