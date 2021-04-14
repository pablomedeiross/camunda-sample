package com.order.process.adapter.sencudary.http.product;

import com.order.process.model.ProductRepository;
import lombok.NonNull;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
class ProductHttpClient implements ProductRepository {

    @NonNull private final String baseUrl;

    ProductHttpClient(@Value("${product-api.baseurl}") String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public boolean productExists(String id) {

        return WebClient
                .create(baseUrl)
                .get()
                .uri("products/{id}", id)
                .exchangeToMono(this::handleResponse)
                .map(Response::isExist)
                .block();
    }

    private Mono<Response> handleResponse(ClientResponse clientResponse) {

        Mono<Response> responseMono;

        if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
            responseMono = Mono.just(new Response());

        } else {
            responseMono = clientResponse
                    .toEntity(Response.class)
                    .map(ResponseEntity::getBody);
        }

        return responseMono;
    }
}
