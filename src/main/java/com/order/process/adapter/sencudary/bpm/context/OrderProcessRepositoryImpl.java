package com.order.process.adapter.sencudary.bpm.context;

import com.order.process.model.OrderProcess;
import com.order.process.model.OrderProcessRepository;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class OrderProcessRepositoryImpl implements OrderProcessRepository {

    @NonNull private final HistoryService historyService;
    @NonNull private final RuntimeService runtimeService;
    @NonNull private final OrderProcessMapper mapper;

    static class NotFoundException extends RuntimeException {}

    @Override
    public void save(OrderProcess orderProcess) {

        this
                .runtimeService
                .setVariables(
                        orderProcess.getId(),
                        mapper.mapToVariables(orderProcess)
                );
    }

    @Override
    public OrderProcess findById(String id) {

        return Optional
                .of(findVariablesFromInstanceId(id))
                .map(vars -> this.mapper.mapToOrderProcess(id, vars))
                .orElseThrow(NotFoundException::new);
    }

    private Map<String, Object> findVariablesFromInstanceId(String id) {

        Map<String, Object> variablesMap = new HashMap<>();

        this
                .historyService
                .createHistoricVariableInstanceQuery()
                .processInstanceId(id)
                .matchVariableNamesIgnoreCase()
                .list()
                .forEach(var -> {
                    if (!variablesMap.containsKey(var.getName())) {
                        variablesMap.put(var.getName(), var.getValue());
                    }
                });

        return variablesMap;
    }

}
