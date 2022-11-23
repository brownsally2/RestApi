package com.kh.RestApi2.dao;

import com.kh.RestApi2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // interface : 선언부만 있으면 됨
    // JpaRepository <>안에 가져올 테이블 이름과 pk의 타입 넣기
    List<Member> findByUserIdAndPwd(String user, String pwd);
    Member findByUserId(String user);
}
