package com.order.process.adapter.sencudary.bpm.delegate;

import com.order.process.application.OrderProcessService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
class ConfirmarDisponibilidadeDelegate implements JavaDelegate {

    private Object legal;
    private final OrderProcessService service;

    ConfirmarDisponibilidadeDelegate(OrderProcessService orderProcessService) {
        this.service = orderProcessService;
    }


    @Override
    public void execute(DelegateExecution execution) throws Exception {


        String id = (String) execution.getVariable("idProduto");


        System.out.println(id);
    }
}
