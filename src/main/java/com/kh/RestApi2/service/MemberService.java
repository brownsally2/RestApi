package com.kh.RestApi2.service;
import com.kh.RestApi2.dao.MemberRepository;
import com.kh.RestApi2.entity.Member;
import com.kh.RestApi2.vo.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service // 서비스 로직을 사용하기 위한 명명, Component 등록과 같음
@Slf4j
public class MemberService {
    private MemberRepository memberRepository;
    // 의존성 주입 : 다른 곳에서 만든 것을 가져다 씀
    // 제어 역전: 주도권을 내어 줌

    // 의존성 주입을 받음
    // 외부의 생성된 MemberRepository를 가져와 씀
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    // 전체 조회
    public List<MemberDTO> getMemberList() {
        List<MemberDTO> memberDTOS = new ArrayList<>();
        // 받아온 데이터를 memberInfo와 같은 모양으로 만들어 줌
        List<Member> memberList = memberRepository.findAll();
        // MemberInfo에 있는 데이터를 memberDTOS에 넣어줌
        for(Member e : memberList){
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setUser(e.getUserId());
            memberDTO.setPwd(e.getPwd());
            memberDTO.setName(e.getName());
            memberDTO.setEmail(e.getEmail());
            memberDTO.setGrade("VIP");
            memberDTOS.add(memberDTO);
        }
        return memberDTOS;
    }
    public MemberDTO getMemberList(String user) {
        // 받아온 데이터를 memberInfo와 같은 모양으로 만들어 줌
        Member member = memberRepository.findByUserId(user);
        // MemberInfo에 있는 데이터를 memberDTOS에 넣어줌
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setUser(member.getUserId());
            memberDTO.setPwd(member.getPwd());
            memberDTO.setName(member.getName());
            memberDTO.setEmail(member.getEmail());
            memberDTO.setGrade("VIP");
            return memberDTO;

    }
    // 회원 가입
    public boolean regMember(String userId, String pwd, String name, String mail) {
        // DTO에서 날라온 데이터를 DB에 넣음
        Member member = new Member();
        member.setUserId(userId);
        member.setPwd(pwd);
        member.setName(name);
        member.setEmail(mail);
        member.setRegData(LocalDateTime.now());
        Member rst = memberRepository.save(member);
        // 진행 확인을 위함
        log.warn(rst.toString());
        return true;
    }
    public boolean loginCheck(String user, String pwd){
        List<Member> memberList = memberRepository.findByUserIdAndPwd(user, pwd);
        for(Member e : memberList){
            // 값이 있으면 true 반환
            return true;
        }
        return false;
    }
}
