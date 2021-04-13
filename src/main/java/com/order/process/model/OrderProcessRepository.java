package com.order.process.model;

public interface OrderProcessRepository {

    void save(OrderProcess productProcess);
    OrderProcess findById(String id);
}
