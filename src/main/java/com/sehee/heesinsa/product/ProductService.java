package com.sehee.heesinsa.product;

import com.sehee.heesinsa.product.dto.RequestCreateProductDTO;
import com.sehee.heesinsa.product.model.Product;
import com.sehee.heesinsa.product.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(RequestCreateProductDTO requestCreateProductDTO) {
        Product product = Product.from(requestCreateProductDTO);
        productRepository.insert(product);
        return product;
    }
}
