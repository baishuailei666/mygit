package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Administrator
 */
public interface UserDao extends JpaRepository<User,Long> {
    /**
     * findByUserName
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * findByUserNameOrEmail
     * @param userName
     * @param email
     * @return
     */
    User findByUserNameOrEmail(String userName, String email);

}
