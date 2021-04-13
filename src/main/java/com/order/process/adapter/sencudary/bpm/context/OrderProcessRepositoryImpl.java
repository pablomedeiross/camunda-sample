package com.order.process.adapter.sencudary.bpm.context;

import com.order.process.model.OrderProcess;
import com.order.process.model.OrderProcessRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
@AllArgsConstructor
class OrderProcessRepositoryImpl implements OrderProcessRepository {

    @NonNull private final HistoryService historyService;
    @NonNull private final RuntimeService runtimeService;
    @NonNull private final RepositoryService repositoryService;
    @NonNull private final OrderProcessMapper mapper;

    @Override
    public void save(OrderProcess orderProcess) {
        runtimeService.setVariables(orderProcess.getId(), mapper.mapToVariables(orderProcess));
    }

    @Override
    public OrderProcess findById(String id) {

        var instance = historyService
                .createHistoricProcessInstanceQuery()
                .processInstanceId("3cb594f0-9ac5-11eb-a43b-02424a3d7275")
                .singleResult();

        historyService.createHistoricVariableInstanceQuery().processInstanceId("" +
                "" +
                "").list()


        historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceId().list();
//        ProcessDefinition process = repositoryService.getProcessDefinition(id);
//        var instance = runtimeService.createProcessInstanceQuery().tenantIdIn(id).list().get(0);

//
//        for(String el : instance.getExecutionIds()) {
//            System.out.println(el);
//        }
        return mapper.mapToOrderProcess(id, new HashMap<String, Object>());
    }
}
