package com.example.processfile.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.processfile.enums.TextFormat.NAME;
import static com.example.processfile.enums.TextFormat.USER_ID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CustomerOrder {
    private Integer userId;
    private String name;

    private Map<Long, Order> ordersMap = new HashMap<>();

    public static CustomerOrder with(final String orderLine) {
        var fieldUserId = orderLine.substring(USER_ID.getStart(), USER_ID.getEnd()).trim();
        var fieldName = orderLine.substring(NAME.getStart(), NAME.getEnd()).trim();

        final var customer = new CustomerOrder();
        customer.userId = Integer.valueOf(fieldUserId);
        customer.name = fieldName;

        return customer;
    }

    public void addOrder(Order orderNew) {
        final var currentOrder = this.ordersMap.putIfAbsent(orderNew.getOrderId(), orderNew);
        if (Objects.nonNull(currentOrder)) {
            final var products = orderNew.getProducts();
            products.forEach(currentOrder::addProduct);
        }
    }

}
