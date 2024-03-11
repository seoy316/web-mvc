package com.ssg.webmvc.member.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

class MemberDAOTest {

    @Test
    public void testConnection() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3308/webdb",
                "webuser",
                "webuser");



        Assertions.assertNotNull(connection);
        connection.close();
    }
}