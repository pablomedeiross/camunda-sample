package com.order.process.adapter.sencudary.bpm.context;

import com.order.process.model.OrderProcess;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
class OrderProcessMapper {

    Map<String, Object> orderProcessToVariablesMap(OrderProcess orderProcess) {

        Map<String, Object> map = new HashMap<>();
        map.put(Variable.PRODUCT_ID.getName(), orderProcess.getIdProduct());
        map.put(Variable.AVAILABLE.getName(), orderProcess.isInStock());
        map.put(Variable.SUCCESS.getName(), orderProcess.isWasNotified());

        return map;
    }

    OrderProcess mapToOrderProcess(String contextId, Map<String, Object> variables) {

        String productId = (String) variables.get(Variable.PRODUCT_ID.getName());
        boolean available = (boolean) variables.get(Variable.AVAILABLE.getName());
        boolean success = (boolean) variables.get(Variable.SUCCESS.getName());

        return OrderProcess
                .builder()
                .id(contextId)
                .idProduct(productId)
                .inStock(available)
                .wasNotified(success)
                .build();
    }

}
