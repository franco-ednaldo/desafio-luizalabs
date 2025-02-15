package com.example.processfile.service;

import com.example.processfile.model.CustomerOrder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OrderService {
     List<CustomerOrder> execute(List<MultipartFile> lines);
}
