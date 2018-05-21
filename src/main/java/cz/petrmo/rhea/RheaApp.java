package cz.petrmo.rhea;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.activiti.engine.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author petrmocz
 *
 */

@Component
public class RheaApp implements Runnable {

	@Autowired
	private RepositoryService	repositoryService;

	private final Logger		log	= LoggerFactory.getLogger(RheaApp.class);

	/**
	 * @param repositoryService
	 *            the repositoryService to set
	 */
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	/**
	 * Rhea application init.
	 */
	@PostConstruct
	public void init() {
		log.info("Rhea initializing ....");
	}

	/**
	 * Rhea application predestroy.
	 */
	@PreDestroy
	public void destroy() {
		log.info("Rhea destroying ...");
	}

	/**
	 * Rhea application run.
	 */
	@Override
	public void run() {
		log.info("Rhea running ...");

		// final ProcessEngine processEngine = ProcessEngines
		// .getDefaultProcessEngine();
		// log.info("Using {} process engine", processEngine.getName());
	}

}
