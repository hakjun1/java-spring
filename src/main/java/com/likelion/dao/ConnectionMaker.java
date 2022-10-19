package com.likelion.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
    public Connection makeConnection() throws SQLException;
}

/*  1. UserDao오버로딩한 ConnectionMaker를 받아서 초기화하는 Constructor가 잘 작동하는지
    2. 새로 바뀐 ConnectionMaker interface를 의존하는 구조도 잘 작동하는지
    3. ConnectionMaker interface 구현한 AwsConnectionMaker가 잘 잘동하는지
    로직은 기존과 같고 구조만 바꿧다
 */

