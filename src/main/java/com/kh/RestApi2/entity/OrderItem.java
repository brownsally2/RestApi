package com.kh.RestApi2.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter@Setter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 한번의 주문에 여러개의 상품이 포함될 수 있으므로 다대일 매핑 관계(item 기준)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY) // 하나의 상품이 여러 회원에게 주문될 수 있으므로 다대일 매핑 관계
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가격
    private int count; // 수량
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
