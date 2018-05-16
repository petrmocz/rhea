package cz.petrmo.rhea;

import java.util.Iterator;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Main application class.
 *
 * @author petrmocz
 *
 */
@SpringBootApplication
public class App {

	/**
	 * Main application startpoint method.
	 *
	 * @param args
	 *            program arguments
	 */
	public static void main(final String[] args) {
		SpringApplication.run(App.class, args);
	}

	/**
	 * Command line runner.
	 *
	 * @param ctx
	 *            context
	 * @return null
	 */
	@Bean
	public final CommandLineRunner commandLineRunner(
			final ApplicationContext ctx) {
		return args -> {

			final ProcessEngine processEngine = (ProcessEngine) ctx
					.getBean("processEngine");
			System.out.format("Configured process engine: %s\n",
					processEngine.getName());
			final RepositoryService repositoryService = processEngine
					.getRepositoryService();

			final List<Deployment> deps = repositoryService
					.createDeploymentQuery().list();

			final Iterator<Deployment> ideps = deps.iterator();
			while (ideps.hasNext()) {
				System.out.println(ideps.next().toString());
			}

		};
	}
}
