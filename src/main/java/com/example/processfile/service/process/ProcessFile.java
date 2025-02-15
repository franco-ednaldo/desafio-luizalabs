package com.example.processfile.service.process;

import com.example.processfile.model.CustomerOrder;

import java.util.List;

public interface ProcessFile {

    List<CustomerOrder> execute(List<String> lines);
}
