package com.sehee.heesinsa.order;

import com.sehee.heesinsa.order.dto.RequestCreateOrUpdateOrderDTO;
import com.sehee.heesinsa.order.dto.ResponseOrderDTO;
import com.sehee.heesinsa.order.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.text.MessageFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

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

    @Transactional(readOnly = true)
    public List<ResponseOrderDTO> readAll() {
        return orderRepository.findAll().stream().map(order -> {
            order.setOrderItems(orderRepository.findAllOrderItemsById(order.getId()));
            return ResponseOrderDTO.from(order);
        }).toList();
    }

    @Transactional(readOnly = true)
    public ResponseOrderDTO readById(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoSuchElementException(
                MessageFormat.format("There is no order with that id: {0}.", orderId.toString())
        ));
        order.setOrderItems(orderRepository.findAllOrderItemsById(orderId));
        return ResponseOrderDTO.from(order);
    }
}
