package com.sehee.heesinsa.order;

import com.sehee.heesinsa.order.model.Order;
import com.sehee.heesinsa.order.model.OrderItem;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.sehee.heesinsa.util.JdbcUtil.EXPECTED_UPDATE_COUNT;

@Repository
public class OrderRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    OrderRepository(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void insert(Order order) {
        int insertedOrderCount = jdbcTemplate.update("INSERT INTO orders(id, email, address, postcode, order_status, created_at, updated_at) " +
                        "VALUES (UUID_TO_BIN(:id), :email, :address, :postcode, :order_status, :created_at, :updated_at)",
                toOrderParamMap(order));

        order.getOrderItems()
                .forEach(item ->
                        jdbcTemplate.update("INSERT INTO order_items(order_id, product_id, quantity) " +
                                        "VALUES (UUID_TO_BIN(:order_id), UUID_TO_BIN(:product_id), :quantity)",
                                toOrderItemParamMap(order.getId(), item)));
        if (insertedOrderCount != EXPECTED_UPDATE_COUNT) {
            throw new IncorrectResultSetColumnCountException(EXPECTED_UPDATE_COUNT, insertedOrderCount);
        }
    }

    private Map<String, Object> toOrderParamMap(Order order) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("id", order.getId().toString().getBytes());
        paramMap.put("email", order.getEmail());
        paramMap.put("address", order.getAddress());
        paramMap.put("postcode", order.getPostcode());
        paramMap.put("order_status", order.getOrderStatus().name());
        paramMap.put("created_at", order.getCreatedAt());
        paramMap.put("updated_at", order.getUpdatedAt());
        return paramMap;
    }

    private Map<String, Object> toOrderItemParamMap(UUID orderId, OrderItem item) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("order_id", orderId.toString().getBytes());
        paramMap.put("product_id", item.productId().toString().getBytes());
        paramMap.put("quantity", item.quantity());
        return paramMap;
    }
}
