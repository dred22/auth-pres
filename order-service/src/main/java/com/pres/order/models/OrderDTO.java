package com.pres.order.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OrderDTO(int orderId, UUID userId, int quantity, String name) {
}
