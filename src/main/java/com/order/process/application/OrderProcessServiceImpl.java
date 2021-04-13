package com.order.process.application;

import com.order.process.model.OrderProcess;
import com.order.process.model.OrderProcessRepository;
import com.order.process.model.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class OrderProcessServiceImpl implements OrderProcessService {

    @NonNull private final OrderProcessRepository orderProcessRepository;
    @NonNull private final ProductRepository productRepository;
    @NonNull private final OrderProcessorStarter startOrderProcessor;

    @Override
    public OrderProcess findOrderProcessById(String id) {
        return orderProcessRepository.findById(id);
    }

    @Override
    public void confirmInStock(String idOrderProcess) {

        OrderProcess orderProcess = orderProcessRepository.findById(idOrderProcess);

        if(orderProcess == null) {
            throw new OrderProcessDontExists();
        }

        orderProcess.markAsInStock();
        orderProcessRepository.save(orderProcess);
    }

    @Override
    public String startOrderProcess(String productId) {

        if (!productRepository.productExists())
            throw new ProductDontExists();

        return startOrderProcessor.start(productId);
    }

    public static class OrderProcessDontExists extends RuntimeException {}
    public static class ProductDontExists extends RuntimeException {}

}
