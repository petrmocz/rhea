package cz.petrmo.rhea;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTestConfiguration.class)
public class AppTest extends TestCase {

	private final Logger		log	= LoggerFactory.getLogger(getClass());

	@Autowired
	private ProcessEngine		processEngine;

	@Autowired
	private RuntimeService		runtimeService;

	@Autowired
	private RepositoryService	repositoryService;

	@SuppressWarnings("unused")
	@Autowired(required = false)
	private TaskService			taskService;

	/**
	 * Create the test case
	 */
	public AppTest() {
		super();
	}

	/**
	 * Rigourous Test :-)
	 */
	@Test
	public void appTest() {
		assertTrue(true);
	}

	/**
	 * Test process engine availability
	 */
	@Test
	public void testProcessEngine() {
		assertNotNull(processEngine);
		assertThat(processEngine, instanceOf(ProcessEngine.class));
	}

	/**
	 * Test runtime service availability
	 */
	@Test
	public void testRuntimeService() {
		assertNotNull(runtimeService);
		assertThat(runtimeService, instanceOf(RuntimeService.class));
	}

	/**
	 * Test loading default process engine config
	 */
	@Test
	public void whenCreateDefaultConfiguration_thenGotProcessEngine() {
		final ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResourceDefault();
		final ProcessEngine processEngine = processEngineConfiguration
				.buildProcessEngine();

		assertNotNull(processEngine);
	}

	/**
	 * Deploy and run instance Script process
	 */
	@Test
	public void createScriptProcessInstance() {
		repositoryService.createDeployment()
				.addClasspathResource("ScriptProcess.bpmn").deploy();
		final ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("ScriptProcess");
		assertNotNull(processInstance);
		System.out.format("Process instance name: %s, id:  %s\n",
				processInstance.getProcessDefinitionName(),
				processInstance.getProcessInstanceId());

	}
}
