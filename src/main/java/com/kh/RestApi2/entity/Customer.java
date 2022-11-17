package com.kh.RestApi2.entity;

import lombok.Data;

import javax.persistence.*;

// getter, setter를 생성해줌
@Entity
@Data
public class Customer {
    //Id가 PK역할을 위해 지정
    @Id
    // 키 값을 생성하는 전략 : 기본키 생성을 JPA기준
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    // 255자만 입력할 수 있으로 증가 시켜줌
//    @Column(length=1024)
    private String address;
    private String firstPhone;
}
