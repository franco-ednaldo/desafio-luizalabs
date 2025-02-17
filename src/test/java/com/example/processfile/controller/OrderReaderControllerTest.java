package com.example.processfile.controller;

import com.example.processfile.mapper.CustomerMapper;
import com.example.processfile.mapper.CustomerMapperImpl;
import com.example.processfile.service.OrderServiceImpl;
import com.example.processfile.service.process.ProcessFileImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.hasSize;

import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(OrderReaderController.class)
@Import({OrderServiceImpl.class, ProcessFileImpl.class, CustomerMapperImpl.class})
class OrderReaderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private CustomerMapper mapper;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Dado um arquivo válido, deve retornar status 200 e a lista de customers com seus orders")
    void givenAValidFileWhenCallApi_thenShouldReturnStatus200AndListCustomer() throws Exception {
        ClassPathResource resource = new ClassPathResource("order.txt");
        MockMultipartFile file = new MockMultipartFile(
                "files", "order.txt", MediaType.TEXT_PLAIN_VALUE, resource.getInputStream()
        );

        mockMvc.perform(
                        multipart("/orders")
                                .file(file)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(8)))
                .andExpect(jsonPath("$[?(@.userId == 70)].name").value("Palmer Prosacco"));
    }

    @Test
    @DisplayName("Dado um arquivo inválido deve ser retornado status 400")
    void givenAnInvalidFileWhenCallApi_thenShouldReturnStatus4000() throws Exception {
        ClassPathResource resource = new ClassPathResource("order2.txt");
        MockMultipartFile file = new MockMultipartFile(
                "files", "order2.txt", MediaType.TEXT_PLAIN_VALUE, resource.getInputStream()
        );

        mockMvc.perform(
                        multipart("/orders")
                                .file(file)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}