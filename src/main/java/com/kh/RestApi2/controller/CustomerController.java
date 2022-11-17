package com.kh.RestApi2.controller;

import com.kh.RestApi2.dao.CustomerRepository;
import com.kh.RestApi2.entity.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    // 필드 생성
    private CustomerRepository repository;
    // 생성자 만들기, 객체 생성하지 않고 사용 : 싱글톤 문법(단하나의 객체만 생성 -> 재사용)
    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }
    // DB에 전송
    @PutMapping("/customer") // 회원 데이터 생성
    public Customer putCustomer(Customer customer){
        // insert와 같음
        return repository.save(customer);
    }
    // save는 insert이므로 생성과 수정이 같음
    // 리턴값을 받기 위해 Customer로 받음
    @PostMapping("/customer") // 회원 정보 수정
    public Customer postCustomer(Customer customer){
        return repository.save(customer);
    }
    // 리턴값이 없어서 void
    @DeleteMapping("/customer") // 회원 정보 삭제
    public void deleteCustomer(int id) {
        repository.deleteById(id);
    }
    // 실제로는 DTO를 만들어서 보내야함
    @GetMapping("/customer") // id로 개별 회원정보 조회
    public Customer getCustomer(int id){
        return repository.findById(id).orElse(null); // 찾는 값이 없으면 null 반환
    }
    @GetMapping("/customer/list") // 회원정보 전체 조회
    public List<Customer> getCustomer() {
        return repository.findAll();
    }
    @GetMapping("/customer/name") // 이름으로 회원정보 조회
    public List<Customer> getCustomer(String name){
        return repository.findByName(name);
    }
    @GetMapping("/customer/address") // 이름으로 회원정보 조회
    public List<Customer> getCustomerAddr(String address){
        return repository.findByAddressLike("%" +address + "%");

    }
    // Like 검색해보기
    // 이름을 검색하는데 같은 이름이면 주소를 내림차순
    @GetMapping("/customer/search")
    public List<Customer> searchCustomer(String name){
//        return repository.findByNameLike("%" + name + "%");
        return repository.findByNameLikeOrderByAddressDesc("%" + name + "%");
    }
    // 이름 또는 주소에 맞는 항목 검색하기 -> 하나만 맞아도 결과 출력됨
    @GetMapping("/customer/name-addr")
    public List<Customer> getCustomerNameAndAddr(String name, String address){
        return repository.findByNameAndAddress(name, address);
    }
//    @GetMapping("/customer/name-addr")
//    public List<Customer> getCustomerNameOrAddr(String name, String address){
//        return repository.findByNameOrAddress(name, address);
//    }
    // Native 쿼리 호출
    @GetMapping("/customer/name-addr-cust")
    public List<Customer> getNativeNameAndAddr(String name, String address){
        return repository.findVipList2(name, address);
    }

}
