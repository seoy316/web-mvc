package com.ssg.webmvc.member.dao;

import com.ssg.webmvc.member.domain.MemberVO;
import com.ssg.webmvc.member.util.ConnectionUtil;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
    public MemberVO isValidUser(String mid, String mpw) throws Exception {
        String sql = "select * from mvc_member where mid =? and mpw= ?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, mid);
        pstmt.setString(2, mpw);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();
        MemberVO member = MemberVO.builder()
                .mid(rs.getString(1))
                .mpw(rs.getString(2))
                .email(rs.getString(3)).build();

        return member;
    }

}
