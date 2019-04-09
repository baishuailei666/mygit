package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void test() throws Exception {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

//        userDao.save(new User("a", "aa123456", "aa@126.com", "aa",formattedDate));
        userDao.save(new User("b", "bb123456", "bb@126.com", "bb",formattedDate));
//        userDao.save(new User("c", "cc123456", "cc@126.com", "cc",formattedDate));
//        userDao.save(new User("d", "dd123456", "dd@126.com", "dd",formattedDate));
//        userDao.save(new User("e", "ee123456", "ee@126.com", "ee",formattedDate));
//        userDao.save(new User("f", "ff123456", "ff@126.com", "ff",formattedDate));

        Assert.assertEquals(6,userDao.findAll().size());
        Assert.assertEquals("bb",userDao.findByUserNameOrEmail("bb","bb@126.com").getNickName());
//        userDao.delete(userDao.findByUserName("b"));
    }

}
