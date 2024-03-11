package com.ssg.webmvc.member.controller;

import com.ssg.webmvc.member.dto.MemberDTO;
import com.ssg.webmvc.member.service.MemberService;
import lombok.extern.log4j.Log4j2;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(name="memberController", urlPatterns="/member/*")
public class MemberController extends HttpServlet {

    private MemberService memberService = MemberService.INSTANCE;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();

        switch (path)  {
            case "/listMembers.do" -> showMemberList(req,resp);
            case "/addMember.do"-> showMemberRegisterForm(req, resp);
            case "/modMemberForm.do" -> showMemberModForm(req, resp);
            case "/showMember.do" -> showMemberInfo(req, resp);
            default -> resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();

        switch (path) {
            case "/addMember.do" -> addMember(req, resp);
            case "/modMember.do" -> updateMember(req, resp);
            case "/delMember.do" -> deleteMember(req, resp);
            default -> resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    public void showMemberList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<MemberDTO> members = memberService.listMember();
            req.setAttribute("members", members);

        } catch (Exception e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/member/listMembers.jsp").forward(req,resp);
    }

    public void showMemberRegisterForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/member/memberForm.jsp").forward(req,resp);
    }

    public void addMember(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDTO member = MemberDTO.builder()
                .mid(req.getParameter("mid"))
                .mpw(req.getParameter("mpw"))
                .mname(req.getParameter("mname"))
                .email(req.getParameter("email"))
                .build();
        try {
            memberService.addMember(member);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/member/listMembers.do");
    }

    public void showMemberModForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mid = req.getParameter("mid");

        try {
            MemberDTO member = memberService.getMember(mid);
            req.setAttribute("member", member);
            req.getRequestDispatcher("/WEB-INF/member/member/modMemberForm.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMember(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDTO member = MemberDTO.builder()
                .mid(req.getParameter("mid"))
                .mpw(req.getParameter("mpw"))
                .email(req.getParameter("emali"))
                .build();

        try {
            memberService.updateMember(member);
            resp.sendRedirect("/member/listMembers.do");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void showMemberInfo(HttpServletRequest req, HttpServletResponse resp) {
        String mid = req.getParameter("mid");
        try {
            MemberDTO member = memberService.getMember(mid);
            req.setAttribute("member", member);
            req.getRequestDispatcher("/WEB-INF/member/showMemberInfo.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void deleteMember(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mid = req.getParameter("mid");
        try {
            memberService.deleteMember(mid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/member/listMembers.do");
    }

}
