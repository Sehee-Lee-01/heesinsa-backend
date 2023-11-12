package com.sehee.heesinsa.order;

import com.sehee.heesinsa.order.dto.RequestCreateOrUpdateOrderDTO;
import com.sehee.heesinsa.order.dto.ResponseOrderDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseOrderDTO create(@RequestBody RequestCreateOrUpdateOrderDTO createOrderDTO) {
        return orderService.create(createOrderDTO);
    }
}
