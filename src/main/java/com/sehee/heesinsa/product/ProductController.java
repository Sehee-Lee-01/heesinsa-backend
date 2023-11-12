package com.sehee.heesinsa.product;

import com.sehee.heesinsa.product.dto.RequestCreateProductDTO;
import com.sehee.heesinsa.product.dto.ResponseProductDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    public ResponseProductDTO create(@Valid @RequestBody RequestCreateProductDTO requestCreateProductDTO) {
        return productService.create(requestCreateProductDTO);
    }

    @DeleteMapping
    public ResponseProductDTO delete(@NotNull @RequestParam UUID id) {
        return productService.delete(id);
    }

    @GetMapping
    public List<ResponseProductDTO> readAll(@RequestParam(required = false) String name) {
        return name == null ? productService.readAll() : productService.readAllByName(name);
    }

    @GetMapping("/{id}")
    public ResponseProductDTO readById(@PathVariable UUID id) {
        return productService.readById(id);
    }
}
