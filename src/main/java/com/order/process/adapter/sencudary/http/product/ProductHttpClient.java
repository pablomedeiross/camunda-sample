package com.order.process.adapter.sencudary.http.product;

import com.order.process.model.ProductRepository;
import org.springframework.stereotype.Component;

@Component
class ProductHttpClient implements ProductRepository {

    @Override
    public boolean productExists() {
        return true;
    }
}
