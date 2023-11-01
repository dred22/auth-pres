package com.pres.order.controllers;

import com.pres.order.models.CreateOrderDTO;
import com.pres.order.models.OrderDTO;
import com.pres.order.services.OrderService;
import com.pres.order.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    public final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    OrderDTO createOrder(@AuthenticationPrincipal Jwt user,
                         @RequestBody CreateOrderDTO createOrderDTO) {
        log.info("Create order request [{}]", user.getId());
        return orderService.create(TokenUtils.getUserId(user), createOrderDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<OrderDTO> getOrders(@AuthenticationPrincipal Jwt user) {
        return orderService.findUserOrders(TokenUtils.getUserId(user));
    }
}
