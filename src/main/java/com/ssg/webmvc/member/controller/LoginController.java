package com.ssg.webmvc.member.controller;

import com.ssg.webmvc.member.dto.MemberDTO;
import com.ssg.webmvc.member.service.LoginService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
@Log4j2
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String mid= req.getParameter("mid");
        String mpw= req.getParameter("mpw");

        try {
            MemberDTO member = LoginService.INSTANCE.login(mid,mpw);
            HttpSession session = req.getSession();

            session.setAttribute("loginInfo", member);
            resp.sendRedirect("/member/listMembers.do");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/login");
        }
    }
}