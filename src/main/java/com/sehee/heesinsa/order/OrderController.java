package com.sehee.heesinsa.order;

import com.sehee.heesinsa.order.dto.RequestCreateOrUpdateOrderDTO;
import com.sehee.heesinsa.order.dto.ResponseOrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ResponseOrderDTO> create(@RequestBody RequestCreateOrUpdateOrderDTO createOrderDTO) {
        ResponseOrderDTO responseOrderDTO = orderService.create(createOrderDTO);
        return ResponseEntity.created(URI.create("/api/orders" + responseOrderDTO.id()))
                .body(responseOrderDTO);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseOrderDTO> readById(@PathVariable UUID orderId) {
        return ResponseEntity.ok(orderService.readById(orderId));
    }

    @GetMapping
    public ResponseEntity<List<ResponseOrderDTO>> readAll() {
        return ResponseEntity.ok(orderService.readAll());
    }
}
