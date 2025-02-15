package com.example.processfile.service;

import com.example.processfile.model.CustomerOrder;
import com.example.processfile.service.process.ProcessFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProcessFile processFile;

    @Override
    public List<CustomerOrder> execute(final List<MultipartFile> files) {
        final var lines = files.stream()
                .flatMap(file -> handleFile(file).stream())
                .toList();

        return this.processFile.execute(lines);
    }

    private List<String> handleFile(final MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), UTF_8))) {

            return reader.lines()
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar o arquivo", e);
        }
    }
}