package com.example.processfile.controller;

import com.example.processfile.mapper.CustomerMapper;
import com.example.processfile.mapper.CustomerMapperImpl;
import com.example.processfile.service.OrderServiceImpl;
import com.example.processfile.service.process.ProcessTextFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(OrderReaderController.class)
@Import({OrderServiceImpl.class, ProcessTextFile.class, CustomerMapperImpl.class})
class OrderReaderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoSpyBean
    private CustomerMapper mapper;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

//    @Test
//    @DisplayName("Dado um arquivo valido deve ser retornado status 200 e a lista de customer com seus orders")
//    void givenAValidFileWhenCallApi_thenShouldReturnStatus200AndListCustomer() throws Exception {
//        ClassPathResource resource = new ClassPathResource("order.txt");
//        MockMultipartFile file = new MockMultipartFile(
//                "files", "order.txt", MediaType.TEXT_PLAIN_VALUE, resource.getInputStream()
//        );
//
//        mockMvc.perform(
//                        multipart("/orders")
//                                .file(file)
//                                .contentType(MediaType.MULTIPART_FORM_DATA)
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(8)));
//    }

//    @Test
//    @DisplayName("Dado um arquivo válido, deve retornar status 200 e a lista de customers com seus orders")
//    void givenAValidFileWhenCallApi_thenShouldReturnStatus200AndListCustomer() throws Exception {
//        // Carregar o arquivo de teste
//        ClassPathResource resource = new ClassPathResource("order.txt");
//        MockMultipartFile file = new MockMultipartFile(
//                "files", "order.txt", MediaType.TEXT_PLAIN_VALUE, resource.getInputStream()
//        );
//
//        // Executar a requisição
//        mockMvc.perform(
//                        multipart("/orders")
//                                .file(file)
//                                .contentType(MediaType.MULTIPART_FORM_DATA)
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(8)))
//
//                // Validar campos de CustomerResponse
//                .andExpect(jsonPath("$[0].userId").value(70))
//                .andExpect(jsonPath("$[0].name").value("Palmer Prosacco"))
//                .andExpect(jsonPath("$[0].orders", hasSize(3)))
//
//                // Validar campos do primeiro OrderResponse do primeiro customer
//                .andExpect(jsonPath("$[0].orders[0].orderId").value(753))
//                .andExpect(jsonPath("$[0].orders[0].total").value(1836.74))
//                .andExpect(jsonPath("$[0].orders[0].date").value("2021-03-08"))
//                .andExpect(jsonPath("$[0].orders[0].products", hasSize(3)))
//
//                // Validar campos de outro customer
//                .andExpect(jsonPath("$[1].userId").value(75))
//                .andExpect(jsonPath("$[1].name").value("Bobbie Batz"))
//                .andExpect(jsonPath("$[1].orders", hasSize(2)))
//                .andExpect(jsonPath("$[1].orders[0].orderId").value(798))
//                .andExpect(jsonPath("$[1].orders[0].total").value(1578.57))
//                .andExpect(jsonPath("$[1].orders[0].date").value("2021-11-16"));
//    }

    @Test
    @DisplayName("Dado um arquivo válido, deve retornar status 200 e a lista de customers com seus orders")
    void givenAValidFileWhenCallApi_thenShouldReturnStatus200AndListCustomer() throws Exception {
        // Carregar o arquivo de teste
        ClassPathResource resource = new ClassPathResource("order.txt");
        MockMultipartFile file = new MockMultipartFile(
                "files", "order.txt", MediaType.TEXT_PLAIN_VALUE, resource.getInputStream()
        );

        // Executar a requisição
        mockMvc.perform(
                        multipart("/orders")
                                .file(file)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(8))) // Garantir que há 8 customers na resposta

                // Validar um customer específico (Palmer Prosacco)
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