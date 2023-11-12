package com.sehee.heesinsa.product;

import com.sehee.heesinsa.product.model.Category;
import com.sehee.heesinsa.product.model.Product;
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
public class ProductRepository {

    private static final RowMapper<Product> rowMapper = (resultSet, i) -> {
        UUID id = toUUID(resultSet.getBytes("id"));
        Category category = Category.valueOf(resultSet.getString("category"));
        String name = resultSet.getString("name");
        long price = resultSet.getLong("price");
        String description = resultSet.getString("description");
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
        return new Product(id, createdAt, category, name, description, price, updatedAt);
    };
    private final NamedParameterJdbcTemplate jdbcTemplate;

    ProductRepository(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private static Map<String, Object> toParamMap(Product product) {
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

    public void insert(Product product) {
        int insertedCount = jdbcTemplate.update("INSERT INTO products(id, name, category, price,description,created_at,updated_at) " +
                        "VALUES(UUID_TO_BIN(:id), :name, :category, :price,:description,:created_at,:updated_at)",
                toParamMap(product)
        );
        if (insertedCount != EXPECTED_UPDATE_COUNT) {
            throw new IncorrectResultSetColumnCountException(EXPECTED_UPDATE_COUNT, insertedCount);
        }
    }

    public List<Product> findAllByName(String name) {
        return jdbcTemplate.query("SELECT * FROM products WHERE name LIKE :name",
                Map.of("name", "%" + name + "%"),
                rowMapper);
    }

    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM products", Collections.emptyMap(), rowMapper);
    }

    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM products WHERE id = UUID_TO_BIN(:id)", Collections.singletonMap("id", id.toString().getBytes()), rowMapper));
    }

    public void delete(UUID id) {
        int deletedCount = jdbcTemplate.update("DELETE FROM products WHERE id = UUID_TO_BIN(:id)", Collections.singletonMap("id", id.toString().getBytes()));
        if (deletedCount != EXPECTED_UPDATE_COUNT) {
            throw new IncorrectResultSetColumnCountException(EXPECTED_UPDATE_COUNT, deletedCount);
        }
    }

    public void update(Product product) {
        int updatedCount = jdbcTemplate.update("UPDATE products SET name = :name, category = :category, description =:description, price=:price " +
                        "WHERE id = UUID_TO_BIN(:id)",
                toParamMap(product));
        if (updatedCount != EXPECTED_UPDATE_COUNT) {
            throw new IncorrectResultSetColumnCountException(EXPECTED_UPDATE_COUNT, updatedCount);
        }
    }
}
