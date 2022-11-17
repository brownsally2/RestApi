package com.kh.RestApi2.dao;

import com.kh.RestApi2.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberInfo, String> {

}
