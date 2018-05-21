package cz.petrmo.rhea.web;

import java.util.List;
import java.util.Map;

import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Servlet implementation class ProcessDefinitions
 */
@Controller
@RequestMapping("activiti")
public class ProcessController {

	private final static Logger	log	= LoggerFactory
			.getLogger(ProcessController.class);

	@Autowired
	protected RepositoryService	repositoryService;

	@Autowired
	ManagementService			managementService;

	/**
	 * @param repositoryService
	 *            the repositoryService to set
	 */
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	@RequestMapping("list")
	public String processList(Map<String, Object> model) {
		final List<ProcessDefinition> pds = repositoryService
				.createProcessDefinitionQuery().list();
		model.put("processDefinitions", pds);
		return "processdefs";
	}

	@GetMapping(value = "/processes")
	public String processList1(Map<String, Object> model) {
		try {
			final ProcessDefinitionQuery processDefinitionQuery = repositoryService
					.createProcessDefinitionQuery().orderByDeploymentId()
					.desc();
			final List<ProcessDefinition> processDefinitionList = processDefinitionQuery
					.list();
			model.put("processDefinitionList", processDefinitionList);
			model.put("count", processDefinitionList.size());
		} catch (final Exception e) {
			log.error(e.toString());
		}
		return "activiti/processes";
	}
}
