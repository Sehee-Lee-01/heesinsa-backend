package com.sehee.heesinsa.product;

import com.sehee.heesinsa.product.dto.RequestCreateProductDTO;
import com.sehee.heesinsa.product.dto.ResponseProductDTO;
import com.sehee.heesinsa.product.model.Product;
import com.sehee.heesinsa.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseProductDTO createProduct(RequestCreateProductDTO requestCreateProductDTO) {
        Product product = Product.from(requestCreateProductDTO);
        productRepository.insert(product);
        return ResponseProductDTO.of(product);
    }

    public List<ResponseProductDTO> readAllByProductName(String name) {
        return productRepository.findAllByName(name)
                .stream()
                .map(ResponseProductDTO::of)
                .toList();
    }
}
