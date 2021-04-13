package com.order.process;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class ProcessApplicationTests extends AbstractProcessEngineRuleTest {

	@Autowired
	public RuntimeService runtimeService;

	@Test
	public void shouldExecuteHappyPath() {
		// given
		String processDefinitionKey = "confirmacao-disponibilidade-produto";

		// when
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceByKey(processDefinitionKey)
				.setVariable("idProduto", "100")
				.setVariable("disponivel", "true")
				.setVariable("legal", "Show")
				.setVariable("sucesso", "mentira")
				.execute();

		// then
		BpmnAwareTests.assertThat(processInstance)
				.isStarted();
//				.task()
//				.hasDefinitionKey("confirmar-disponibilidade-prod")
//		.isNotNull();
	}
}
