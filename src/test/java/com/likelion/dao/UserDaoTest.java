package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)//Junit 테스트코드에서 스프링을쓰기위해 없으면@Autowired실행안됨
@ContextConfiguration(classes = UserDaoFactory.class)//Junit5 테스트코드에서 설정정보를 불러오게 해주는 기능
class UserDaoTest {

    @Autowired
    ApplicationContext context;
    /*  이것을 사용하기 위해서는 @ExtendWith,@ContextConfiguration을 추가해야한다
        userDao = context 재사용 하는 부분이 많은 코드(add(),select(),delete();;
        스프링부트에서 반복되는 작업을 묶어논것을 Applicationcontext = ioc컨테이너 = spring
        springboot = spring + WAS(Tomcat,jetty 등)
        팩토리를 보며 구조를 파악한다
        실행될때 팩토리가 로드된다
        @Configuration이 붙은 class를 스캔해서 spring이 new UserDao()를 한다
     */


    @Test
    void testQuery() {
       UserDao userDao = context.getBean("awsUserDao",UserDao.class);
               //new UserDaoFactory().awsUserDao(); //팩토리적용
       //UserDao userDao = new UserDao(new AwsConnectionMaker()); //UserDao오버로딩 작동확인
        //생성자를 통해 다른 구현체를 넣어도 로직은 동일하다
        String id = "33";
        userDao.add(new User(id,"hakjun1","1234"));

        User user = userDao.select(id);
        Assertions.assertEquals("hakjun1",user.getName());
        Assertions.assertEquals("1234",user.getPassword());

    }
}