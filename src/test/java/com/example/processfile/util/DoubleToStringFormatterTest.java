package com.example.processfile.util;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class DoubleToStringFormatterTest {

    @Test
    void shouldFormatDoubleWithTwoDecimalPlaces() {
        Double value = 123.456;

        String formatted = DoubleToStringFormatter.formatDouble(value);

        assertThat(formatted).isEqualTo("123.46"); // Arredondamento correto
    }

    @Test
    void shouldFormatDoubleWithTwoDecimalsWhenLessThanTwoProvided() {
        Double value = 123.4;

        String formatted = DoubleToStringFormatter.formatDouble(value);

        assertThat(formatted).isEqualTo("123.40"); // Deve adicionar o zero extra
    }

    @Test
    void shouldFormatZeroCorrectly() {
        Double value = 0.0;

        String formatted = DoubleToStringFormatter.formatDouble(value);

        assertThat(formatted).isEqualTo("0.00");
    }

    @Test
    void shouldFormatNegativeValuesCorrectly() {
        Double value = -45.678;

        String formatted = DoubleToStringFormatter.formatDouble(value);

        assertThat(formatted).isEqualTo("-45.68");
    }

    @Test
    void shouldReturnNullWhenInputIsNull() {
        String formatted = DoubleToStringFormatter.formatDouble(null);
        assertThat(formatted).isNull();
    }
}
