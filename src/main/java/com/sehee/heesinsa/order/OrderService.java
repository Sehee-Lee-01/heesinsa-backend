package com.sehee.heesinsa.order;

import com.sehee.heesinsa.order.dto.RequestCreateOrUpdateOrderDTO;
import com.sehee.heesinsa.order.dto.ResponseOrderDTO;
import com.sehee.heesinsa.order.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public ResponseOrderDTO create(RequestCreateOrUpdateOrderDTO createOrderDTO) {
        Order order = new Order(createOrderDTO.email(), createOrderDTO.orderItems(), createOrderDTO.address(), createOrderDTO.postcode());
        orderRepository.insert(order);
        return ResponseOrderDTO.from(order);
    }
}
