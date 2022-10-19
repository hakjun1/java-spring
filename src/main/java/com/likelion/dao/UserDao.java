package com.likelion.dao;

import com.likelion.domain.User;

import java.sql.*;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao() {
        this.connectionMaker = new AwsConnectionMaker();
    }

    public UserDao(ConnectionMaker connectionMaker) {//di받을수있게 생성자 오버로딩
        this.connectionMaker = connectionMaker;
    }//new UserDao(여기에 ConnectionMaker구현체를 넣는다 (Aws,Local...);

    // UserDao에서는 ConnectionMaker interface를 의존하고있기때문에 구현체를 모두 사용할수있다
    public void add(User user) {
        Connection c;
        try {
            c = connectionMaker.makeConnection();
            PreparedStatement pstmt = c.prepareStatement("INSERT INTO users" +
                    "(id,name,password) values(?,?,?)");
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());

            pstmt.executeUpdate();

            pstmt.close();
            c.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public User select(String id) {
        Connection c;
        try {
            c = connectionMaker.makeConnection();
            PreparedStatement pstmt = c.prepareStatement("SELECT*FROM users where id =?");
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
            rs.close();
            pstmt.close();
            c.close();

            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        Connection c;
        try {
            c = connectionMaker.makeConnection();
            PreparedStatement ps = c.prepareStatement("delete from users");

            ps.executeUpdate();
            ps.close();
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCount() {
        Connection c;

        try {
            c = connectionMaker.makeConnection();
            PreparedStatement pstmt = c.prepareStatement("select count(*) from users");

            ResultSet rs = pstmt.executeQuery();

            rs.next();
            int count = rs.getInt(1);
            rs.close();
            pstmt.close();
            c.close();

            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}


