package com.example.processfile.controller.adivice.error;

import com.example.processfile.exception.BusinessException;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiErrors {
    private List<String> erros;

    public ApiErrors(BindingResult bindingResult) {
        this.erros = new ArrayList<>();
        bindingResult.getAllErrors().forEach(error -> this.erros.add(error.getDefaultMessage()));
    }

    public ApiErrors(BusinessException businessException) {
        this.erros = Arrays.asList(businessException.getMessage());
    }

    public List<String> getErrors() {
        return erros;
    }
}