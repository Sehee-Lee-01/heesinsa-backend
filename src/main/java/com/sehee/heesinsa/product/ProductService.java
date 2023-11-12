package com.sehee.heesinsa.product;

import com.sehee.heesinsa.product.dto.RequestCreateOrUpdateProductDTO;
import com.sehee.heesinsa.product.dto.ResponseProductDTO;
import com.sehee.heesinsa.product.model.Category;
import com.sehee.heesinsa.product.model.Product;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.text.MessageFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Validated
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ResponseProductDTO create(RequestCreateOrUpdateProductDTO createProductDTO) {
        Product product = Product.from(createProductDTO);
        productRepository.insert(product);
        return ResponseProductDTO.of(product);
    }

    @Transactional(readOnly = true)
    public List<ResponseProductDTO> readAll() {
        return productRepository.findAll()
                .stream()
                .map(ResponseProductDTO::of)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ResponseProductDTO> readAllByName(@NotBlank String name) {
        return productRepository.findAllByName(name)
                .stream()
                .map(ResponseProductDTO::of)
                .toList();
    }

    @Transactional(readOnly = true)
    public Product readById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        MessageFormat.format("There is no product with that id: {0}.", id.toString())
                ));
        return product;
    }

    @Transactional
    public ResponseProductDTO delete(UUID id) {
        ResponseProductDTO responseProductDTO = ResponseProductDTO.of(readById(id));
        productRepository.delete(id);
        return responseProductDTO;
    }

    @Transactional
    public ResponseProductDTO update(UUID id, RequestCreateOrUpdateProductDTO updateProductDTO) {
        Product product = readById(id);
        product.setCategory(Category.valueOf(updateProductDTO.category()));
        product.setName(updateProductDTO.name());
        product.setDescription(updateProductDTO.description());
        product.setPrice(updateProductDTO.price());
        productRepository.update(product);
        return ResponseProductDTO.of(readById(id));
    }
}

