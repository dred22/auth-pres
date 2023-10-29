package com.pres.order.services;

import com.pres.order.entity.OrderEntity;
import com.pres.order.mapper.OrderMapper;
import com.pres.order.models.CreateOrderDTO;
import com.pres.order.models.OrderDTO;
import com.pres.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderDTO create(UUID userId, CreateOrderDTO createOrderDTO) {
        OrderEntity orderEntity = orderMapper.createOrderDtoToEntity(userId, createOrderDTO);
        OrderEntity savedEntity = orderRepository.save(orderEntity);
        return orderMapper.entityToOrderDTO(savedEntity);
    }

    public List<OrderDTO> findUserOrders(UUID userId) {
        return orderRepository.findAllByUserId(userId)
                .stream()
                .map(orderMapper::entityToOrderDTO)
                .toList();
    }

    public List<OrderDTO> findAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::entityToOrderDTO)
                .toList();
    }
}
