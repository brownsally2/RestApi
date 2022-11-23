package com.kh.RestApi2.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

// 테이블 생성
@Data // 변경점이 없는 게터 세터 생성자를 만들때는 사용
@Entity // 테이블과 같음
@Table(name="member") // db테이블 명명법으로 테이블 이름 만듦
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // GenerationType 때문에 id값이 자동 증가이기때문에 int나 long으로 만듦
    private Long memberId;
    private String userId;
    private String pwd;
    private String name;
    @Column(unique = true)
    private String email;
    private LocalDateTime regData;
}
