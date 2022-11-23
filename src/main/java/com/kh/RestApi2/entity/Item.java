package com.kh.RestApi2.entity;

import com.kh.RestApi2.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Getter @Setter @ToString
public class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 상품 코드
    @Column(nullable = false, length = 50) // null이 안됨
    private String itemName; // 상품명
    @Column(name = "price", nullable = false)
    private int price; // 가격
    @Column(nullable = false)
    private int stockNumber; // 재고 수량
    @Lob
    @Column(nullable = false)
    private String itemDetail; // 상품의 상세 설명
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; // sell, soldout만 가져 올 수 있음
    private LocalDateTime regTime; // 제품 등록 시간
    private LocalDateTime updateTime; // 수정 시간

}
