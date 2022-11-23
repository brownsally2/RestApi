package com.kh.RestApi2.dao;

import com.kh.RestApi2.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberRepositoryTest {
    @Autowired // 자동 연결, 의존성 주입의 문법(내가 만들진 않고 다른 곳(spring)에서 필요한 것을 가져옴)
    // 그러기 위해서 bean에 등록 해야함, 아무대서나 의존성 주입을 받을 수 있음, 독립적 관계, 생성자가 있으면 안 넣어줘도 됨
    MemberRepository memberRepository; // Autowired로 인해 객체를 생성하지 않고 가져옴
    // 객체로 생성하면 구현하기 위한 코드를 생성 해줌
    // 등록하면 싱글톤이 됨 (static으로 생성되어 한번만 사용 가능하여 지웠다가 썼다가 함)
    @Test
    @DisplayName("회원 가입 테스트")
    public void regMemberTest() {
        for(int i = 1; i <= 10; i++){
            // 객체 생성해주어야 함
            Member member = new Member();
            member.setUserId("JKS2024" + i);
            member.setPwd("SPHB8250");
            member.setName("곰돌이사육사" + i);
            member.setEmail("jks2024@gmail.com" + i);
            member.setRegData(LocalDateTime.now());
            // DB에 넣어줘야 함
            memberRepository.save(member);
        }
    }
}