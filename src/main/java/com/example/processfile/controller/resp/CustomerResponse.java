package com.example.processfile.controller.resp;

import java.util.List;

public record CustomerResponse(
        Integer userId,
        String name,
        List<OrderResponse> orders
) {
}
