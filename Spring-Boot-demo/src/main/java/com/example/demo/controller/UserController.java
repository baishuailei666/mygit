package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 */
@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    /**
     * getUserList
     *
     * @return
     */
    @GetMapping(value = "/getUser")
    public List<User> getUserList() {
        return userDao.findAll();
    }

    /**
     * addUser
     *
     * @param userName
     * @param passWord
     * @param email
     * @param nickName
     * @param regTime
     * @return
     */
    @PostMapping(value = "addUser")
    public User addUser(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord, @RequestParam("email") String email, @RequestParam("nickName") String nickName, @RequestParam("regTime") String regTime) {
        User user = new User();
        user.setUserName(userName);
        user.setPassWord(passWord);
        user.setEmail(email);
        user.setNickName(nickName);
        user.setRegTime(regTime);
        return userDao.save(user);
    }

    /**
     * findByUserName
     *
     * @param userName
     * @return
     */
    @GetMapping(value = "/findByUserName/{userName}")
    public User findByUserName(@PathVariable("userName") String userName) {
        return userDao.findByUserName(userName);
    }


    /**
     * updateUser
     *
     * @param userName
     * @param passWord
     * @param email
     * @param nickName
     * @return
     */
    @PutMapping(value = "/updateUser/{userName}")
    public User updateUser(@PathVariable("userName") String userName, @RequestParam("passWord") String passWord, @RequestParam("email") String email, @RequestParam("nickName") String nickName){
        User user = new User();
        user.setUserName(userName);
        user.setPassWord(passWord);
        user.setEmail(email);
        user.setNickName(nickName);
        return userDao.save(user);
    }

    /**
     * deleteUser
     *
     * @param userName
     */
    @DeleteMapping(value = "deleteUser/{userName}")
    public void deleteUser(@PathVariable("userName") String userName){
        userDao.delete(userDao.findByUserName(userName));
    }
}
