package com.example.processfile.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateConverterTest {

    @Test
    @DisplayName("Dado uma string válida quando formatada então deve retornar um LocalDate correspondente")
    void givenValidString_whenFormat_thenShouldReturnLocalDate() {
        String dateStr = "20240215";
        LocalDate expectedDate = LocalDate.of(2024, 2, 15);

        LocalDate result = LocalDateConverter.formatStringToLocalDate(dateStr);

        assertEquals(expectedDate, result);
    }

    @Test
    @DisplayName("Dado uma string inválida quando formatada então deve lançar exceção")
    void givenInvalidString_whenFormat_thenShouldThrowException() {
        String invalidDateStr = "invalid_date";

        assertThrows(Exception.class, () -> LocalDateConverter.formatStringToLocalDate(invalidDateStr));
    }
}
