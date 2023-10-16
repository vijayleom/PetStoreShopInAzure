package com.postgres.jdbc.data.products;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.azure.core.credential.TokenCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;

import lombok.extern.slf4j.Slf4j;

@Configuration
@ComponentScan("com.postgres.jdbc.data.products")
@Slf4j
public class SpringJDBCConfig {
	
	@Value("#{environment.APPSETTING_KEY_VAULT_NAME}")
    private String keyVaultName;
	
	@Value("#{environment.APPSETTING_TENANT_ID}")
    private String tenantId;
	
	@Value("#{environment.APPSETTING_CLIENT_ID}")
    private String clientId;
	
	@Value("#{environment.APPSETTING_CLIENT_SECRET}")
    private String clientSecret;
	
	@Value("#{environment.APPSETTING_DB_DRIVER_CLASS}")
	private String driverClass;
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
	   return new JdbcTemplate(dataSource());
	}
	
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		
		String kvUrl = "https://"+keyVaultName+".vault.azure.net/";
		log.info("Values of url: {} ", kvUrl);
		
		TokenCredential clientSecretCredential = new ClientSecretCredentialBuilder()
			     .tenantId(tenantId)
			     .clientId(clientId)
			     .clientSecret(clientSecret)
			     .build();
		
		log.info("Values of tenantId: {}, ====== clientId:{} ", tenantId, clientId);
		
		SecretClient secretClient = new SecretClientBuilder()
			    .vaultUrl(kvUrl)
			    .credential(clientSecretCredential)
			    .buildClient();
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClass);
        String jdbcId = secretClient.getSecret("postgreSQLUrl").getId();
        String jdbcName = secretClient.getSecret("postgreSQLUrl").getName();
        String jdbcUrl = secretClient.getSecret("postgreSQLUrl").getValue();
        String jdbcUsr = secretClient.getSecret("postgresuser").getValue();
        String jdbcPwd = secretClient.getSecret("postgrespass").getValue();

        log.info("Values of jdbcId: {}, jdbcName:{}, jdbcUrl:{} ", jdbcId, jdbcName, jdbcUrl);
        log.info("Values of user:{}, pass:{} ", jdbcUsr, jdbcPwd);

        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsr);
        dataSource.setPassword(jdbcPwd);
		
        return dataSource;
    }
}
