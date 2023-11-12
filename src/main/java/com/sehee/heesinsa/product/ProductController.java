package com.sehee.heesinsa.product;

import com.sehee.heesinsa.product.dto.RequestCreateOrUpdateProductDTO;
import com.sehee.heesinsa.product.dto.ResponseProductDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//TODO: apply ResponseEntity
@RequestMapping("/api/products")
@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseProductDTO create(@Valid @RequestBody RequestCreateOrUpdateProductDTO createProductDTO) {
        return productService.create(createProductDTO);
    }

    @PutMapping("/{id}")
    public ResponseProductDTO update(@PathVariable UUID id, @Valid @RequestBody RequestCreateOrUpdateProductDTO updateProductDTO) {
        return productService.update(id, updateProductDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseProductDTO delete(@PathVariable UUID id) {
        return productService.delete(id);
    }

    @GetMapping
    public List<ResponseProductDTO> readAll(@RequestParam(required = false) String name) {
        return name == null ? productService.readAll() : productService.readAllByName(name);
    }

    @GetMapping("/{id}")
    public ResponseProductDTO readById(@PathVariable UUID id) {
        return ResponseProductDTO.of(productService.readById(id));
    }
}
