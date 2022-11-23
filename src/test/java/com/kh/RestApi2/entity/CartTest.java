package com.kh.RestApi2.entity;

import com.kh.RestApi2.dao.CartRepository;
import com.kh.RestApi2.dao.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // 일을 한번에 처리하는 단위 ( ex. 이체할 때)
@Slf4j
@TestPropertySource(locations = "classpath:application-test.properties")

class CartTest {
    @Autowired // 의존성 주입(bean에 등록)
    CartRepository cartRepository;
    @Autowired
    MemberRepository memberRepository;
    @PersistenceContext // 영속성과 관련된 정보를 보관하고 있는 객체 (영속성 - 항상 값이 유지)
    EntityManager em; // DB와 관련된 객체를 관리 -> 연관 관계가 있을때만 필요
    public Member createMember() {
        Member member = new Member();
        member.setUserId("JKS2024");
        member.setPwd("SPHB8250");
        member.setName("곰돌이사육사");
        member.setEmail("jks2024@gmail.com");
        member.setRegData(LocalDateTime.now());
        return member;
    }
    @Test
    @DisplayName("장바구니 회원 매팽 테스트")
    public void findCartAndMemberTest(){
        Member member = createMember();
        memberRepository.save(member); // 값이 DB에 저장

        Cart cart = new Cart();
        // cart는 member를 의존하고 있음
        cart.setMember(member); // cart 객체에 member 객체를 대입
        cartRepository.save(cart);
        // member객체와 cart안에 있는 member객체로 두개 존재
        em.flush(); // 영속성 컨텍스트에 데이터 저장 후 트랙잭션이 끝날 때 DB에 반영
        // 트랜잭션 필요 (memberRepository, cartRepository 두가지를 저장해야 함)
        em.clear(); // 버퍼 비우기
        Cart savedCart = cartRepository.findById(cart.getId())
                .orElseThrow(EntityNotFoundException::new);
        // 디버깅, 조건 처리
        assertEquals(savedCart.getMember().getUserId(), member.getUserId());
    }
}