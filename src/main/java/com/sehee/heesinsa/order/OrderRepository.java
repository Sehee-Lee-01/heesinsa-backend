package com.sehee.heesinsa.order;

import com.sehee.heesinsa.order.model.Order;
import com.sehee.heesinsa.order.model.OrderItem;
import com.sehee.heesinsa.order.model.OrderStatus;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.*;

import static com.sehee.heesinsa.util.JdbcUtil.EXPECTED_UPDATE_COUNT;
import static com.sehee.heesinsa.util.JdbcUtil.toUUID;

@Repository
public class OrderRepository {
    private static final RowMapper<Order> orderRowMapperper = (resultSet, i) -> {
        UUID id = toUUID(resultSet.getBytes("id"));
        String email = resultSet.getString("email");
        String address = resultSet.getString("address");
        String postcode = resultSet.getString("postcode");
        OrderStatus orderStatus = OrderStatus.valueOf(resultSet.getString("order_status"));
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
        return new Order(id, email, createdAt, address, postcode, orderStatus, updatedAt);
    };
    private static final RowMapper<OrderItem> orderItemRowMapper = (resultSet, i) -> {
        UUID orderId = toUUID(resultSet.getBytes("order_id"));
        UUID productId = toUUID(resultSet.getBytes("product_id"));
        int quantity = Integer.parseInt(resultSet.getString("quantity"));
        return new OrderItem(orderId, productId, quantity);
    };
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

    public List<Order> findAll() {
        return jdbcTemplate.query("SELECT * FROM orders", Collections.emptyMap(), orderRowMapperper);
    }

    public Optional<Order> findById(UUID orderId) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject("SELECT * FROM orders WHERE id = UUID_TO_BIN(:id)",
                        Collections.singletonMap("id", orderId.toString().getBytes()), orderRowMapperper)
        );
    }

    public List<OrderItem> findAllOrderItemsById(UUID orderId) {
        return jdbcTemplate.query("SELECT * FROM order_items WHERE order_id = UUID_TO_BIN(:order_id)",
                Collections.singletonMap("order_id", orderId.toString().getBytes()), orderItemRowMapper);
    }
}
