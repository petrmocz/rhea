package cz.petrmo.rhea;

import javax.sql.DataSource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class ApplicationTestConfiguration {

	@Bean
	public DataSource dataSource() {
		final SimpleDriverDataSource inMemoryDataSource = new SimpleDriverDataSource();
		inMemoryDataSource.setUsername("sa");
		inMemoryDataSource.setPassword("");
		inMemoryDataSource.setUrl(
				"jdbc:h2:tcp://localhost/activititest;DB_CLOSE_ON_EXIT=FALSE");
		inMemoryDataSource.setDriverClass(org.h2.Driver.class);

		return inMemoryDataSource;
	}

	/*
	 * @Bean public LocalContainerEntityManagerFactoryBean
	 * entityManagerFactoryBean( DataSource dataSource) { final
	 * LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new
	 * LocalContainerEntityManagerFactoryBean();
	 * entityManagerFactoryBean.setDataSource(dataSource);
	 * entityManagerFactoryBean.setPackagesToScan("domain"); final
	 * JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
	 * entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
	 * entityManagerFactoryBean.setJpaProperties(hibernateProperties());
	 * 
	 * return entityManagerFactoryBean; }
	 */

	/*
	 * private Properties hibernateProperties() { final Properties properties =
	 * new Properties(); properties.setProperty("hibernate.show_sql", "true");
	 * properties.setProperty("hibernate.format_sql", "true");
	 * 
	 * return properties; }
	 */

	/*
	 * @Bean
	 * 
	 * public PlatformTransactionManager transactionManager(
	 * EntityManagerFactory entityManagerFactory) { final JpaTransactionManager
	 * transactionManager = new JpaTransactionManager();
	 * transactionManager.setEntityManagerFactory(entityManagerFactory); return
	 * transactionManager; }
	 */
	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public SpringProcessEngineConfiguration processEngineConfiguration(
			DataSource dataSource,
			PlatformTransactionManager transactionManager) {
		final SpringProcessEngineConfiguration engineConfiguration = new SpringProcessEngineConfiguration();
		engineConfiguration.setDataSource(dataSource);
		engineConfiguration.setTransactionManager(transactionManager);

		engineConfiguration.setHistory(HistoryLevel.FULL.getKey());
		engineConfiguration.setDatabaseSchemaUpdate(
				SpringProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP);

		return engineConfiguration;
	}

	@Bean
	public ProcessEngineFactoryBean processEngineFactoryBean(
			SpringProcessEngineConfiguration springProcessEngineConfiguration) {
		final ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
		processEngineFactoryBean.setProcessEngineConfiguration(
				springProcessEngineConfiguration);
		return processEngineFactoryBean;
	}

	@Bean
	public RepositoryService repositoryService(
			ProcessEngineFactoryBean processEngineFactoryBean)
			throws Exception {
		return processEngineFactoryBean.getProcessEngineConfiguration()
				.getRepositoryService();
	}

	@Bean
	public RuntimeService runtimeService(
			ProcessEngineFactoryBean processEngineFactoryBean)
			throws Exception {
		return processEngineFactoryBean.getProcessEngineConfiguration()
				.getRuntimeService();
	}

	@Bean
	public HistoryService historyService(
			ProcessEngineFactoryBean processEngineFactoryBean)
			throws Exception {
		return processEngineFactoryBean.getProcessEngineConfiguration()
				.getHistoryService();
	}

	@Bean
	public ManagementService managementService(
			ProcessEngineFactoryBean processEngineFactoryBean)
			throws Exception {
		return processEngineFactoryBean.getProcessEngineConfiguration()
				.getManagementService();
	}

	@Bean
	public IdentityService identityService(
			ProcessEngineFactoryBean processEngineFactoryBean)
			throws Exception {
		return processEngineFactoryBean.getProcessEngineConfiguration()
				.getIdentityService();
	}

	@Bean
	public FormService formService(
			ProcessEngineFactoryBean processEngineFactoryBean)
			throws Exception {
		return processEngineFactoryBean.getProcessEngineConfiguration()
				.getFormService();
	}

	@Bean
	public TaskService taskService(
			ProcessEngineFactoryBean processEngineFactoryBean)
			throws Exception {
		return processEngineFactoryBean.getProcessEngineConfiguration()
				.getTaskService();
	}

}
