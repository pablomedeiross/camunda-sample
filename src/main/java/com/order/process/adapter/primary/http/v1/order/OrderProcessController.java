package com.order.process.adapter.primary.http.v1.order;

import com.order.process.application.OrderProcessService;
import com.order.process.model.OrderProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RequestMapping("v1/orders-process")
public class OrderProcessController {

    @Autowired private OrderProcessService service;

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderProcess> get(@PathVariable("id") String id) {

        OrderProcess orderProcess = service.findOrderProcessById(id);

        if(orderProcess == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(orderProcess);
    }

    @PostMapping
    public ResponseEntity<String> post(@RequestBody String productId) {

        String idOrderProcess = service.startOrderProcess(productId);
        return ResponseEntity.ok(idOrderProcess);
    }

}
