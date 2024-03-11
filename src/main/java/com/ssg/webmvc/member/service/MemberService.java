package com.ssg.webmvc.member.service;

import com.ssg.webmvc.member.dao.MemberDAO;
import com.ssg.webmvc.member.domain.MemberVO;
import com.ssg.webmvc.member.dto.MemberDTO;
import com.ssg.webmvc.member.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum MemberService {
    INSTANCE;

    private ModelMapper modelMapper;
    private MemberDAO memberDAO;
    MemberService() {
        memberDAO = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public List<MemberDTO> listMember() throws Exception {
        List<MemberVO> voList = memberDAO.selectAll();
        List<MemberDTO> dtoList = voList.stream()
                .map(vo-> modelMapper.map(vo, MemberDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    public void addMember(MemberDTO member) throws Exception {
        MemberVO vo = modelMapper.map(member, MemberVO.class);
        int state = memberDAO.insert(vo);

        if (state!=0)
            log.info("회원가입 성공");
        else
            log.info("회원가입 실패");
    }

    public void deleteMember(String mid) throws Exception {
        int state = memberDAO.delete(mid);

        if (state!=0)
            log.info("회원 삭제 성공");
        else
            log.info("회원 삭제 실패");
    }

    public void updateMember(MemberDTO member) throws Exception {
        MemberVO vo = modelMapper.map(member, MemberVO.class);
        int state = memberDAO.update(vo);

        if (state!=0)
            log.info("회원 정보 수정 성공");
        else
            log.info("회원 정보 수정 실패");
    }

    public MemberDTO getMember(String mid) throws Exception {
        MemberVO vo = memberDAO.selectOne(mid);
        return modelMapper.map(vo, MemberDTO.class);
    }

}
