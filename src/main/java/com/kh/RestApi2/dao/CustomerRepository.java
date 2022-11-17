package com.kh.RestApi2.dao;

import com.kh.RestApi2.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Entity가 Customer, id가 pk이므로 Integer(객체로 만듦)
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // 명명 규칙에 의해 쿼리문 생성
    List<Customer> findByName(String name);
    List<Customer> findByAddressLike(String address);
    List<Customer> findByNameLike(String name);
    // 정렬 조건
    List<Customer> findByNameLikeOrderByAddressDesc(String name);
    List<Customer> findByNameOrAddress(String name, String address);
    List<Customer> findByNameAndAddress(String name, String address);

    // Native 쿼리 방법
    @Query(value = "select * from Customer where name = ?1 and address = ?2" , nativeQuery = true)
    List<Customer> findVipList2(String name, String address);

    // JPQL(Java Persistence Query Language)
    @Query("from Customer where name = ?1 and address = ?2")
    List<Customer> findVipList1(String name, String address);

}
