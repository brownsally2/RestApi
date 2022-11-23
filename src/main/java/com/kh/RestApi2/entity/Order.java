package com.kh.RestApi2.entity;

import com.kh.RestApi2.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // order,join는 예약어
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    @ManyToOne // 한명의 회원은 여러번 주문할 수 있으므로 다대일 관계
    @JoinColumn(name = "member_id")
    private Member member;
    private LocalDateTime orderDate; // 주문일
    @Enumerated(EnumType.STRING) // 문자 그자체로 나타남
    private OrderStatus orderStatus; // 주문 상태

    // mappedBy = "order": mappedBy 옵션은 주인이 아닌쪽에 사용
    // cascade = CascadeType.ALL : 부모 엔티티의 영속성 상태 변화를 자식 엔티티에 모두 전이
    // orphanRemoval = true : 고아객체 제거
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();


    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
