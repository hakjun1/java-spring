package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.crypto.ExemptionMechanismException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;
    UserDao userDao;

    User user1;
    User user2;
    User user3;

    @BeforeEach
    void setup() {
        this.userDao = context.getBean("awsUserDao",UserDao.class);
        this.user1 = new User("1","hakjun1","123");
        this.user2 = new User("2","hakjun2","456");
        this.user3 = new User("3","hakjun3","789");
    }

    @Test
    void addandselect() throws SQLException, ClassNotFoundException {
        userDao.deleteAll();
        assertEquals(0,userDao.getCount());
        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);
        assertEquals(3,userDao.getCount());

        User user = userDao.select(user3.getId());
        assertEquals(user3.getName(),user.getName());
        System.out.println(user.getId());
    }
    @Test
    void count() throws SQLException {
        userDao.deleteAll();
        assertEquals(0,userDao.getCount());
    }
//    @Test
//    void select(){
//        assertThrows(ExemptionMechanismException.class, () ->{
//            userDao.select("30");});
//    }

}