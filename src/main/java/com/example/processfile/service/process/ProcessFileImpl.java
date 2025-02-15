package com.example.processfile.service.process;

import com.example.processfile.exception.ErrorParserFile;
import com.example.processfile.model.CustomerOrder;
import com.example.processfile.model.Order;
import com.example.processfile.model.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.processfile.enums.TextFormat.USER_ID;

@Component
public class ProcessFileImpl implements ProcessFile {

    @Override
    public List<CustomerOrder> execute(final List<String> lines) {
        try {
            if (lines == null || lines.size() == 0) {
                throw new ErrorParserFile("Empty file");
            }

            Map<Integer, CustomerOrder> mapCustomer = new HashMap<>();
            for (String line : lines) {
                var fieldUserId = line.substring(USER_ID.getStart(), USER_ID.getEnd()).trim();
                final var userId = Integer.valueOf(fieldUserId);

                final var customerNew = mapCustomer.containsKey(userId) ?
                        mapCustomer.get(userId) :  CustomerOrder.with(line);

                final var orderNew = Order.with(line);

                orderNew.addProduct(Product.with(line));
                customerNew.addOrder(orderNew);
                mapCustomer.put(customerNew.getUserId(), customerNew);
            }
            return mapCustomer.values().stream().toList();
        } catch (Exception ex) {
            throw new ErrorParserFile("File processing error", ex);
        }
    }
}
