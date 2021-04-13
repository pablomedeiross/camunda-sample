package com.order.process.application;

import com.order.process.model.OrderProcess;

public interface OrderProcessService {

    String startOrderProcess(String productId);
    OrderProcess findOrderProcessById(String id);
    void confirmInStock(String idOrderProcess);
}
