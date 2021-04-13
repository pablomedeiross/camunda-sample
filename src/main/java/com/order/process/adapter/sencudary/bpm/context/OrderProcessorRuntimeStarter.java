package com.order.process.adapter.sencudary.bpm.context;

import com.order.process.application.OrderProcessorStarter;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.apache.tomcat.jni.Proc;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class OrderProcessorRuntimeStarter implements OrderProcessorStarter {

    private final RuntimeService runtimeService;
    private final String processKey;

    OrderProcessorRuntimeStarter(RuntimeService runtimeService,
                                 @Value("${process-key}") String processKey) {

        this.runtimeService = runtimeService;
        this.processKey = processKey;
    }

    @Override
    public String start(String productId) {

       return runtimeService
                .createProcessInstanceByKey(processKey)
                .setVariable(Variable.PRODUCT_ID.getName(), productId)
                .setVariable(Variable.AVAILABLE.getName(), false)
                .setVariable(Variable.SUCCESS.getName(), false)
                .execute()
               .getProcessInstanceId();
    }
}
