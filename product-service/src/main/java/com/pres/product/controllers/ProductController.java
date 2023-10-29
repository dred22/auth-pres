package com.pres.product.controllers;

import com.pres.product.model.ProductDTO;
import com.pres.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    public final ProductService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ProductDTO> createOrder(@AuthenticationPrincipal Jwt principal) {
        log.info("Get products request by [{}]", principal.getId());
        return orderService.findAll();
    }

}
