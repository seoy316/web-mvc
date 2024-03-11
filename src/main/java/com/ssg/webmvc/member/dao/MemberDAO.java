package com.ssg.webmvc.member.dao;

import com.ssg.webmvc.member.domain.MemberVO;
import com.ssg.webmvc.member.dto.MemberDTO;
import com.ssg.webmvc.member.util.ConnectionUtil;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    public int insert(MemberVO member) throws Exception {
        String sql = "INSERT INTO mvc_member(mid, mpw, mname, email) VALUES (?, ?, ?, ?)";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, member.getMid());
        pstmt.setString(2, member.getMpw());
        pstmt.setString(3, member.getMname());
        pstmt.setString(4, member.getEmail());

        return pstmt.executeUpdate();
    }

    public List<MemberVO> selectAll() throws Exception {
        List<MemberVO> memberList = new ArrayList<>();
        String sql = "select * from mvc_member";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            MemberVO vo = MemberVO.builder()
                    .mid(rs.getString("mid"))
                    .mpw(rs.getString("mpw"))
                    .mname(rs.getString("mname"))
                    .email(rs.getString("email"))
                    .created(rs.getDate("created").toLocalDate())
                            .build();
            memberList.add(vo);
        }
        return memberList;
    }

    public MemberVO selectOne(String mid) throws Exception {
        MemberVO vo = null;
        String sql = "select * from mvc_member where mid=?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, mid);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            vo = MemberVO.builder()
                     .mid(rs.getString("mid"))
                     .mpw(rs.getString("mpw"))
                     .mname(rs.getString("mname"))
                     .email(rs.getString("email"))
                     .build();
        }
        return vo;
    }

    public int delete(String mid) throws Exception {
        String sql = "delete from mvc_member where mid=?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, mid);

        return pstmt.executeUpdate();
    }

    public int update(MemberVO member) throws Exception {
        String sql = "UPDATE mvc_member SET mpw=?, email=? WHERE mid=?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, member.getMpw());
        pstmt.setString(2, member.getEmail());
        pstmt.setString(3, member.getMid());

        return pstmt.executeUpdate();
    }
}
