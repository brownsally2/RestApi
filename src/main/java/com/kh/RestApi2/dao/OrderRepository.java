package com.kh.RestApi2.dao;

import com.kh.RestApi2.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
