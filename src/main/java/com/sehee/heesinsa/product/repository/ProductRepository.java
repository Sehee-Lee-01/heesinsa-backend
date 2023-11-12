package com.sehee.heesinsa.product.repository;

import com.sehee.heesinsa.product.model.Product;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {
    private static final int EXPECTED_INSERT_COUNT = 1;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    ProductRepository(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void insert(Product product) {
        int insertedCount = jdbcTemplate.update("INSERT INTO products(id, name, category, price,description,created_at,updated_at) " +
                        "VALUES(UUID_TO_BIN(:id), :name, :category, :price,:description,:created_at,:updated_at)",
                toParamMap(product)
        );
        if (insertedCount != EXPECTED_INSERT_COUNT) {
            throw new IncorrectResultSetColumnCountException(EXPECTED_INSERT_COUNT, insertedCount);
        }
    }

    public Map<String, Object> toParamMap(Product product) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", product.getId().toString().getBytes());
        paramMap.put("category", product.getCategory().name());
        paramMap.put("name", product.getName());
        paramMap.put("price", product.getPrice());
        paramMap.put("description", product.getDescription());
        paramMap.put("created_at", product.getCreatedAt());
        paramMap.put("updated_at", product.getUpdatedAt());
        return paramMap;
    }
}
