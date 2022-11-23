package com.kh.RestApi2.entity;

import com.kh.RestApi2.constant.OrderStatus;
import com.kh.RestApi2.dao.ItemRepository;
import com.kh.RestApi2.dao.MemberRepository;
import com.kh.RestApi2.dao.OrderItemRepository;
import com.kh.RestApi2.dao.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Slf4j
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional // 하나의 명령어 단위로 묶임
class OrderTest {
    @Autowired // 의존성 주입
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @PersistenceContext // 하나가 아니여서 관리 필요
    EntityManager em;

    // 아이템 한개를 추가
    public Item createItem(){
        Item item = new Item();
        item.setItemName("테스트 상품");
        item.setPrice(1000);
        item.setItemDetail("상세설명");
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        return item;
    }

    @Test
    @DisplayName("영속성 전이 테스트")
    @Rollback(value = false) // 테스트 결과를 rollback시키지 않음
    public void cascadeTest() {
        Order order = new Order();
        order.setOrderStatus(OrderStatus.ORDER);
        for(int i = 0; i < 3; i++) {
            Item item = this.createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem); // 주문 아이템을 생성해서 주문 목록에 추가
        }
        // order 엔티티를 저장하면서 강제로 flush를 호출하여 영속성 컨텍스트에 있는 객체들을 DB에 반영
        orderRepository.saveAndFlush(order);
        em.clear(); // 영속성 컨텍스트를 초기화

        Order savedOrder = orderRepository.findById(order.getId()).orElseThrow(EntityNotFoundException::new);
        // itemOrder 엔티티가 3개가 실제로 DB에 저장되어 있는지를 검사. 예상값과 같으면 결과를 출력
        assertEquals(3, savedOrder.getOrderItems().size());
    }
    public Order createOrder(){
        Order order = new Order();
        for(int i = 0; i<3; i++){
            Item item = createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        Member member = new Member();
        memberRepository.save(member);

        order.setMember(member);
        orderRepository.save(order);
        return order;
    }
    @Test
    @DisplayName("고아객체 제거 테스트")
    public void orphanRemovalTest() {
        Order order = this.createOrder();
        order.getOrderItems().remove(0); // 해당하는 orderItem가 지워짐
        em.flush();
    }
    @Test
    @DisplayName("지연 로딩 테스트")
    public void lazyLoadingTest() {
        Order order = this.createOrder();
        Long orderItemId = order.getOrderItems().get(0).getId();
        em.flush(); // 원하는 시점에 DB에 강제 반영
        em.clear();
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                // 예외 처리
                .orElseThrow(EntityNotFoundException::new);
        log.warn("Order class : " + orderItem.getOrder().getClass());
    }



}