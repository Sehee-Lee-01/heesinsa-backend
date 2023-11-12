package com.sehee.heesinsa.product;

import com.sehee.heesinsa.product.dto.RequestCreateProductDTO;
import com.sehee.heesinsa.product.dto.ResponseProductDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: apply ResponseEntity
@RequestMapping("/api/products")
@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseProductDTO createProduct(@Valid @RequestBody RequestCreateProductDTO requestCreateProductDTO) {
        return productService.createProduct(requestCreateProductDTO);
    }

    @GetMapping("/{name}")
    public List<ResponseProductDTO> readAllByProductName(@NotBlank @PathVariable String name) {
        return productService.readAllByProductName(name);
    }
}
