package com.pres.product.service;

import com.pres.product.model.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public List<ProductDTO> findAll() {
        return List.of(new ProductDTO(1, "first product"),
                new ProductDTO(1, "second product"),
                new ProductDTO(1, "third product"));
    }
}
