package com.example.processfile.service;

import com.example.processfile.model.CustomerOrder;
import com.example.processfile.service.process.ProcessFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private ProcessFile processFile;

    @InjectMocks
    private OrderServiceImpl orderService;

    private MultipartFile mockFile;

    @BeforeEach
    void setup() throws Exception {
        String fileContent = "order1\norder2\norder3";
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());

        mockFile = mock(MultipartFile.class);
        when(mockFile.getInputStream()).thenReturn(inputStream);
    }

    @Test
    void givenValidFiles_whenExecute_thenShouldProcessSuccessfully() {
        List<MultipartFile> files = List.of(mockFile);
        List<String> expectedLines = List.of("order1", "order2", "order3");
        List<CustomerOrder> mockOrders = List.of(new CustomerOrder(), new CustomerOrder());

        when(processFile.execute(expectedLines)).thenReturn(mockOrders);

        List<CustomerOrder> result = orderService.execute(files);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(processFile, times(1)).execute(expectedLines);
    }

    @Test
    void givenInvalidFile_whenExecute_thenShouldThrowException() throws Exception {
        when(mockFile.getInputStream()).thenThrow(new RuntimeException("File error"));

        List<MultipartFile> files = List.of(mockFile);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> orderService.execute(files));
        assertTrue(exception.getMessage().contains("Erro ao processar o arquivo"));
    }
}
