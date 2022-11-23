package com.kh.RestApi2.dao;

import com.kh.RestApi2.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
