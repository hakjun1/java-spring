package com.likelion.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//spring의 시작 springboot는 @RestController,@Controller
public class UserDaoFactory {
    //조립( 선풍기 날개)
    //관계설정기능 어떤 조합을 사용할 것인지

    @Bean
    public UserDao awsUserDao(){
        AwsConnectionMaker awsConnectionMaker = new AwsConnectionMaker();
        UserDao userDao = new UserDao();
        return userDao;
    }
    @Bean
    public UserDao localUserDao(){
//        LocalConnectionMaker localConnectionMaker = new LocalConnectionMaker();
//        UserDao userDao = new UserDao(localConnectionMaker);
        UserDao userDao = new UserDao(new LocalConnectionMaker());
        return userDao;
    }
}
