package com.example.processfile.enums;

public enum TextFormat {
    USER_ID(0, 10),
    NAME(10, 55),
    ORDER_ID(55, 65),
    PRODUCT_ID(65, 75),
    VALUE_PRODUCT(75, 87),
    DATE(87, 95);

    private final int start;
    private final int end;

    private TextFormat(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    //    private CustomerOrder parseLine(String line) {
//        try {
//            String userId = line.substring(0, 10).trim();
//            String userName = line.substring(10, 55);
//            String orderId = line.substring(55, 65).trim();

//            String productId = line.substring(65, 75).trim();
//            double value = Double.parseDouble(line.substring(75, 87).trim());
//            String dateString = line.substring(87, 95).trim();
//            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
//            return new CustomerOrder(userId, userName, orderId, productId, value, date);
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Erro ao processar linha: " + line, e);
//        }
//    }
}
