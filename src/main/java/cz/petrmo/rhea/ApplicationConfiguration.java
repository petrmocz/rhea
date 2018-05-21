package cz.petrmo.rhea;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.thymeleaf.TemplateEngine;

/**
 * @author petrmocz
 *
 */
@Configuration
@EnableTransactionManagement
public class ApplicationConfiguration {

	private final Logger	log	= LoggerFactory
			.getLogger(ApplicationConfiguration.class);

	/*
	 * @Bean public Runnable rheaApp() { return new RheaApp(); }
	 */
	@Autowired
	private Environment		env;

	/*
	 * @Bean public DataSource dataSource() { final DriverManagerDataSource
	 * actDataSource = new DriverManagerDataSource(); actDataSource
	 * .setUsername(env.getProperty("spring.datasource.username"));
	 * actDataSource
	 * .setPassword(env.getProperty("spring.datasource.password"));
	 * actDataSource.setUrl(env.getProperty("spring.datasource.url"));
	 * actDataSource.setDriverClassName(
	 * env.getProperty("spring.datasource.driver-class-name"));
	 * 
	 * log.info("Database connection: {}", actDataSource.getUrl()); return
	 * actDataSource; }
	 */

	/*
	 * @Bean public EntityManagerFactory entityManagerFactory(
	 * LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) { return
	 * entityManagerFactoryBean.getNativeEntityManagerFactory(); }
	 */

	/*
	 * @Bean public LocalContainerEntityManagerFactoryBean
	 * entityManagerFactoryBean( DataSource dataSource) { final
	 * LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new
	 * LocalContainerEntityManagerFactoryBean();
	 * entityManagerFactoryBean.setDataSource(dataSource);
	 * entityManagerFactoryBean.setPackagesToScan("cz.petrmo.rhea.domain");
	 * final JpaVendorAdapter jpaVendorAdapter = new
	 * HibernateJpaVendorAdapter();
	 * entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
	 * entityManagerFactoryBean.setJpaProperties(hibernateProperties());
	 * 
	 * return entityManagerFactoryBean; }
	 * 
	 * private Properties hibernateProperties() { final Properties properties =
	 * new Properties(); properties.setProperty("hibernate.show_sql", "true");
	 * properties.setProperty("hibernate.format_sql", "true");
	 * 
	 * return properties; }
	 */

	@Bean
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	/*
	 * @Bean public PlatformTransactionManager txManager() { return new
	 * DataSourceTransactionManager(dataSource()); }
	 */

	@Bean
	public SpringProcessEngineConfiguration processEngineConfiguration(
			DataSource dataSource,
			PlatformTransactionManager transactionManager,
			EntityManagerFactory entityManagerFactory) {
		final SpringProcessEngineConfiguration engineConfiguration = new SpringProcessEngineConfiguration();
		engineConfiguration.setDataSource(dataSource);
		engineConfiguration.setTransactionManager(transactionManager);
		engineConfiguration.setJpaEntityManagerFactory(entityManagerFactory);
		engineConfiguration.setHistory(HistoryLevel.FULL.getKey());
		engineConfiguration.setDatabaseSchemaUpdate(
				SpringProcessEngineConfiguration.DB_SCHEMA_UPDATE_DROP_CREATE);

		log.info("Process Engine Name: {}",
				engineConfiguration.getProcessEngineName());
		return engineConfiguration;
	}

	@Bean
	public ProcessEngine processEngine(
			SpringProcessEngineConfiguration springProcessEngineConfiguration)
			throws Exception {
		final ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
		processEngineFactoryBean.setProcessEngineConfiguration(
				springProcessEngineConfiguration);
		return processEngineFactoryBean.getObject();
	}

	/*
	 * @Bean public ProcessEngineFactoryBean processEngineFactoryBean(
	 * SpringProcessEngineConfiguration springProcessEngineConfiguration) {
	 * final ProcessEngineFactoryBean processEngineFactoryBean = new
	 * ProcessEngineFactoryBean();
	 * processEngineFactoryBean.setProcessEngineConfiguration(
	 * springProcessEngineConfiguration); return processEngineFactoryBean; }
	 */

	@Bean
	public RepositoryService repositoryService(ProcessEngine processEngine)
			throws Exception {
		final RepositoryService rs = processEngine.getRepositoryService();
		log.info("Repository Service {}", rs.toString());
		return rs;
	}

	@Bean
	public RuntimeService runtimeService(ProcessEngine processEngine)
			throws Exception {
		return processEngine.getRuntimeService();
	}

	@Bean
	public HistoryService historyService(ProcessEngine processEngine)
			throws Exception {
		return processEngine.getHistoryService();
	}

	@Bean
	public ManagementService managementService(ProcessEngine processEngine)
			throws Exception {
		return processEngine.getManagementService();
	}

	@Bean
	public IdentityService identityService(ProcessEngine processEngine)
			throws Exception {
		return processEngine.getIdentityService();
	}

	@Bean
	public FormService formService(ProcessEngine processEngine)
			throws Exception {
		return processEngine.getFormService();
	}

	@Bean
	public TaskService taskService(ProcessEngine processEngine)
			throws Exception {
		return processEngine.getTaskService();
	}

	/*
	 * @Bean public ServletContextTemplateResolver templateResolver() throws
	 * Exception { final ServletContextTemplateResolver s = new
	 * ServletContextTemplateResolver( null); s.setPrefix("/WEB-INF/templates");
	 * s.setSuffix(".html"); s.setTemplateMode("HTML5"); return s;
	 * 
	 * }
	 */

	@Bean
	public TemplateEngine templateEngine() throws Exception {
		final TemplateEngine t = new TemplateEngine();

		return t;
	}

}
