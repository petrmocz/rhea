package cz.petrmo.rhea;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author petrmocz
 *
 */
@Component
public class RheaApp implements Runnable {

	private final Logger log = LoggerFactory.getLogger(RheaApp.class);

	@PostConstruct
	public void init() {
		log.info("Rhea initializing ....");
	}

	@PreDestroy
	public void destroy() {
		log.info("Rhea destroying ...");
	}

	@Override
	public void run() {
		log.info("Rhea running ...");
		// final ProcessEngine processEngine = ProcessEngines
		// .getDefaultProcessEngine();
		// log.info("Using {} process engine", processEngine.getName());
	}

}
