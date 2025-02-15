package com.example.processfile.controller.resp;


import java.time.LocalDate;
import java.util.List;

public record OrderResponse(
        Long orderId,
        Double total,

        LocalDate date,

        List<ProductResponse> products
) {
}
