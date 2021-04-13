package com.order.process.model;


import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class OrderProcess {

    @NonNull private final String id;
    @NonNull private final String idProduct;
    private boolean inStock;
    private boolean wasNotified;

    public void markAsInStock() {
        this.inStock = true;
    }
}
