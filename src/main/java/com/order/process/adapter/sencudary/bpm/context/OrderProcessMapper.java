package com.order.process.adapter.sencudary.bpm.context;

import com.order.process.model.OrderProcess;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
class OrderProcessMapper {

    private static final String PRODUCT_ID = "idProduto";
    private static final String AVAILABLE = "disponivel";
    private static final String SUCCESS = "sucesso";

    Map<String, Object> mapToVariables(OrderProcess orderProcess) {

        Map<String, Object> map = new HashMap<>();
        map.put(PRODUCT_ID, orderProcess.getIdProduct());
        map.put(AVAILABLE, orderProcess.isInStock());
        map.put(SUCCESS, orderProcess.isWasNotified());

        return map;
    }

    OrderProcess mapToOrderProcess(String contextId, Map<String, Object> variables) {

        String productId = (String) variables.get(PRODUCT_ID);
        boolean available = (boolean) variables.get(AVAILABLE);
        boolean success = (boolean) variables.get(SUCCESS);


        return OrderProcess
                .builder()
                .id(contextId)
                .idProduct(productId)
                .inStock(available)
                .wasNotified(success)
                .build();
    }

}
