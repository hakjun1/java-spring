package com.likelion.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class LocalConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeConnection() throws SQLException {
        return null;
        //여기에 로컬 getconnection을 입력하고 UserDao의 생성자에 넣을수있다
        //interface를 구현했을때의 유연성
    }
}
