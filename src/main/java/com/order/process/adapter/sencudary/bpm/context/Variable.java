package com.order.process.adapter.sencudary.bpm.context;

import lombok.Getter;
import lombok.NonNull;

@Getter
enum Variable {

    PRODUCT_ID("idProduto"),
    AVAILABLE("disponivel"),
    SUCCESS("sucesso");

    @NonNull private final String name;

    Variable(String name) {
        this.name = name;
    }
}
