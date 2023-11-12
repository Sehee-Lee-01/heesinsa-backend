package com.sehee.heesinsa.product;

import com.sehee.heesinsa.product.dto.RequestCreateOrUpdateProductDTO;
import com.sehee.heesinsa.product.dto.ResponseProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/products")
@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ResponseProductDTO> create(@Valid @RequestBody RequestCreateOrUpdateProductDTO createProductDTO) {
        ResponseProductDTO createdProduct = productService.create(createProductDTO);
        return ResponseEntity.created(URI.create("/api/products/" + createdProduct.id()))
                .body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> update(@PathVariable UUID id, @Valid @RequestBody RequestCreateOrUpdateProductDTO updateProductDTO) {
        return ResponseEntity.ok(productService.update(id, updateProductDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.delete(id));
    }

    @GetMapping
    public ResponseEntity<List<ResponseProductDTO>> readAll(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(name == null ? productService.readAll() : productService.readAllByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> readById(@PathVariable UUID id) {
        return ResponseEntity.ok(ResponseProductDTO.of(productService.readById(id)));
    }
}
