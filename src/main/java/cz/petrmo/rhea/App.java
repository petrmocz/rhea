package cz.petrmo.rhea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

/**
 * Main application class.
 *
 * @author petrmocz
 *
 */

@SpringBootApplication
@EnableAutoConfiguration
@ContextConfiguration(classes = ApplicationConfiguration.class)
// @ImportResource("classpath:spring.xml")
public class App extends SpringBootServletInitializer {

	/**
	 * @return RheaApp
	 */
	@Bean
	RheaApp rheaApp() {
		return new RheaApp();

	}

	/**
	 * Repository Service
	 */
	/*
	 * @Autowired RepositoryService repositoryService;
	 */

	/**
	 * Logger
	 */
	private final Logger log = LoggerFactory.getLogger(App.class);

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
		final ApplicationContext context = SpringApplication.run(App.class,
				args);
		final Runnable app = context.getBean(RheaApp.class);
		app.run();
	}

	/**
	 * Command line runner.
	 *
	 * @param ctx
	 *            context
	 * @return null
	 */
	/*
	 * @Bean public CommandLineRunner commandLineRunner(final ApplicationContext
	 * ctx) { return args -> {
	 * 
	 * final ProcessEngine processEngine = (ProcessEngine) ctx
	 * .getBean("processEngine"); log.info("Configured process engine: {}",
	 * processEngine.getName()); final RepositoryService repositoryService =
	 * processEngine .getRepositoryService();
	 * 
	 * final List<Deployment> deps = repositoryService
	 * .createDeploymentQuery().list();
	 * 
	 * final Iterator<Deployment> ideps = deps.iterator(); while
	 * (ideps.hasNext()) { System.out.println(ideps.next().toString()); }
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

}
