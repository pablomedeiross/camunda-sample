package com.order.process.adapter.sencudary.bpm.context;

import com.order.process.application.OrderProcessorStarter;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class OrderProcessorRuntimeStarter implements OrderProcessorStarter {

    @NonNull private final RuntimeService runtimeService;
    private static final String PROCESS_KEY = "confirmacao-disponibilidade-produto";

    @Override
    public String start(String productId) {

        return runtimeService
                .createProcessInstanceByKey(PROCESS_KEY)
                .setVariable("idProduto", productId)
                .setVariable("disponivel", false)
                .setVariable("sucesso", false)
                .execute()
                .getProcessInstanceId();
    }
}
