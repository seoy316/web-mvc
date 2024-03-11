package com.ssg.webmvc.member.service;

import com.ssg.webmvc.member.dao.LoginDAO;
import com.ssg.webmvc.member.domain.MemberVO;
import com.ssg.webmvc.member.dto.MemberDTO;
import com.ssg.webmvc.member.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

@Log4j2
public enum LoginService {
    INSTANCE;

    private LoginDAO loginDAO;
    private ModelMapper modelMapper;

    LoginService() {
        loginDAO = new LoginDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public MemberDTO login(String mid, String mpw) throws Exception {
        MemberVO vo = loginDAO.isValidUser(mid, mpw);

        if (vo == null) {
            log.info("로그인 실패");
            return null;
        }

        return modelMapper.map(vo, MemberDTO.class);
    }

}
