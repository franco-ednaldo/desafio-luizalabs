package com.example.processfile.mapper;

import com.example.processfile.controller.resp.CustomerResponse;
import com.example.processfile.controller.resp.OrderResponse;
import com.example.processfile.model.CustomerOrder;
import com.example.processfile.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = OrderMapper.class)
public interface CustomerMapper {

    @Mapping(target = "orders", expression = "java(mapOrders(customerOrder.getOrdersMap()))")
    CustomerResponse toResponse(CustomerOrder customerOrder);
    List<CustomerResponse> toResponse(List<CustomerOrder> customerOrder);

    default List<OrderResponse> mapOrders(Map<Long, Order> ordersMap) {
        return ordersMap.values().stream()
                .map(this::convertOrder)
                .collect(Collectors.toList());
    }

    default OrderResponse convertOrder(Order order) {
        return OrderMapper.INSTANCE.toResponse(order);
    }
}



