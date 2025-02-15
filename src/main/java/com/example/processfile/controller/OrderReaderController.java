package com.example.processfile.controller;

import com.example.processfile.controller.resp.CustomerResponse;
import com.example.processfile.mapper.CustomerMapper;
import com.example.processfile.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderReaderController {
    private final OrderService orderService;

    private final CustomerMapper mapper;

    @PostMapping
    public List<CustomerResponse> getOrders(@RequestParam("files") final List<MultipartFile> files) {
        log.info("#### START PROCESS FILES ###");
        final var response = orderService.execute(files);
        log.info("#### FINISHED PROCESS FILES ###");
        return mapper.toResponse(response);
    }
}
