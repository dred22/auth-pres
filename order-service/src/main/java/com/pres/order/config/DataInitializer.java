package com.pres.order.config;

import com.pres.order.entity.OrderEntity;
import com.pres.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements ApplicationRunner {
    private final OrderRepository orderRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        OrderEntity orderEntity = new OrderEntity(0, UUID.fromString("98bbcc3c-5176-4660-bc9e-2f0b0b8276a8"), 1, "init");
        orderRepository.save(orderEntity);
        orderEntity = new OrderEntity(0, UUID.randomUUID(), 1, "init");
        orderRepository.save(orderEntity);
        log.info("DATA  INITIALIZER [{}]", orderRepository.findAll());

    }
}
