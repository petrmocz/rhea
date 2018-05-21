package cz.petrmo.rhea;

import java.util.Arrays;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
// import org.springframework.test.context.ContextConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main application class.
 *
 * @author petrmocz
 *
 */

@SpringBootApplication
@EnableAutoConfiguration
// @EnableJpaRepositories("cz.petrmo.rhea.domain")
@ComponentScan(value = "cz.petrmo.rhea")
@Configuration
// @ContextConfiguration(classes = ApplicationConfiguration.class)
// @ImportResource("classpath:spring.xml")
public class App extends SpringBootServletInitializer
		implements CommandLineRunner {

	/**
	 * @return RheaApp
	 */
	@Bean
	RheaApp rheaApp() {
		return new RheaApp();

	}

	@Value("${spring.application.name}")
	String						appName;

	@Autowired
	private IdentityService		identityService;

	@Autowired
	private ApplicationContext	appContext;

	@Autowired
	private ProcessEngine		processEngine;

	/**
	 * @param identityService
	 *            the identityService to set
	 */
	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

	/**
	 * @param processEngine
	 *            the processEngine to set
	 */
	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}

	/**
	 * Logger
	 */
	private final static Logger log = LoggerFactory.getLogger(App.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.boot.web.servlet.support.SpringBootServletInitializer
	 * #configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(App.class);
	}

	/**
	 * Main application startpoint method.
	 *
	 * @param args
	 *            program arguments
	 */
	public static void main(final String[] args) {
		SpringApplication.run(App.class, args);

		// final Runnable rheaApp = appContext.getBean(RheaApp.class);
		// rheaApp.run();
		// App.createGroupAndUsers(context);
	}

	/**
	 * Command line runner.
	 *
	 * @param ctx
	 *            context
	 * @return null
	 */

	// @Bean
	/*
	 * public CommandLineRunner commandLineRunner(final ApplicationContext ctx)
	 * { return args -> {
	 * 
	 * final ProcessEngine processEngine = (ProcessEngine) ctx
	 * .getBean("processEngineFactoryBean");
	 * log.info("Configured process engine: {}", processEngine.getName()); final
	 * RepositoryService repositoryService = processEngine
	 * .getRepositoryService();
	 * 
	 * final List<Deployment> deps = repositoryService
	 * .createDeploymentQuery().list();
	 * 
	 * final Iterator<Deployment> ideps = deps.iterator(); while
	 * (ideps.hasNext()) { log.info(ideps.next().toString()); }
	 * 
	 * final ProcessDefinitionQuery processDefinitionQuery = repositoryService
	 * .createProcessDefinitionQuery(); final List<ProcessDefinition>
	 * processDefs = processDefinitionQuery .list();
	 * 
	 * log.info("Configured process definitions:"); for (final ProcessDefinition
	 * pd : processDefs) { log.info("{} {} {} {} ", pd.getId(),
	 * pd.getCategory(), pd.getName(), pd.getResourceName()); }
	 * 
	 * }; }
	 */

	public void createGroupAndUsers(IdentityService identityService) {

		final Group group1 = identityService.newGroup("admin");
		identityService.saveGroup(group1);

		final Group group2 = identityService.newGroup("manager");
		identityService.saveGroup(group2);

		final Group group3 = identityService.newGroup("users");
		identityService.saveGroup(group3);

		final User adminUser = identityService.newUser("admin");
		adminUser.setPassword("pass");
		adminUser.setEmail("admin@localhost");
		adminUser.setFirstName("Admin");
		adminUser.setLastName("User");
		identityService.saveUser(adminUser);

		final User user1 = identityService.newUser("user1");
		user1.setPassword("pass");
		user1.setEmail("user1@localhost");
		user1.setFirstName("Regular ");
		user1.setLastName("User 1");
		identityService.saveUser(user1);

		identityService.createMembership("admin", "admin");
		identityService.createMembership("user1", "users");
	}

	@SuppressWarnings("unused")
	@Override
	public void run(String... args) throws Exception {
		final String[] beans = appContext.getBeanDefinitionNames();
		Arrays.sort(beans);

		for (final String bean : beans) {
			// log.info("Bean: {}", bean);
		}

		log.info("Application name:", appName);
		createGroupAndUsers(identityService);
	}
}
