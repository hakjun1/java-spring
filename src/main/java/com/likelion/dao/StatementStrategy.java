package com.likelion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {//Strategy패턴 interface를 의존하게해서 구현체를

    //필요에 따라 선택할 수 있게 하는 패턴
    PreparedStatement makePreparedStatement(Connection connection) throws SQLException;
}
