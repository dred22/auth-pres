package com.pres.order.mapper;

import com.pres.order.entity.OrderEntity;
import com.pres.order.models.CreateOrderDTO;
import com.pres.order.models.OrderDTO;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface OrderMapper {


    OrderEntity createOrderDtoToEntity(UUID userId, CreateOrderDTO createOrderDTO);

    OrderDTO entityToOrderDTO(OrderEntity orderEntity);
}
