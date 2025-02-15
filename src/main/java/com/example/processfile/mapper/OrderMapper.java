package com.example.processfile.mapper;

import com.example.processfile.controller.resp.OrderResponse;
import com.example.processfile.model.Order;
import com.example.processfile.util.DoubleToStringFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "total", target = "total", qualifiedByName = "formatDouble")
    OrderResponse toResponse(Order order);

    List<OrderResponse> toResponse(List<Order> orders);

    @Named("formatDouble")
    default String formatDouble(Double total) {
        return DoubleToStringFormatter.formatDouble(total);
    }
}
