package com.pres.order.controllers;

import com.pres.order.models.OrderDTO;
import com.pres.order.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class OrderAdminController {

    public final OrderService orderService;

    @GetMapping
    List<OrderDTO> getOrders(){
        return orderService.findAllOrders();
    }
}
