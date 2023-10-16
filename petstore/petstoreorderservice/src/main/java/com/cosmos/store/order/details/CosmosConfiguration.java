package com.cosmos.store.order.details;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.credential.TokenCredential;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosDiagnosticsHandler;
import com.azure.cosmos.DirectConnectionConfig;
import com.azure.cosmos.GatewayConnectionConfig;
import com.azure.cosmos.models.CosmosClientTelemetryConfig;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableCosmosRepositories(basePackages = "com.cosmos.store.order.details.repository")
@Slf4j
public class CosmosConfiguration extends AbstractCosmosConfiguration {

	@Value("#{environment.APPSETTING_KEY_VAULT_NAME}")
    private String keyVaultName;
	
	@Value("#{environment.APPSETTING_TENANT_ID}")
    private String tenantId;
	
	@Value("#{environment.APPSETTING_CLIENT_ID}")
    private String clientId;
	
	@Value("#{environment.APPSETTING_CLIENT_SECRET}")
    private String clientSecret;

	private String database;
	
	private AzureKeyCredential azureKeyCredential;

	@Bean
    public CosmosClientBuilder getCosmosClientBuilder() {
		String kvUrl = "https://"+keyVaultName+".vault.azure.net/";
		TokenCredential clientSecretCredential = new ClientSecretCredentialBuilder()
			     .tenantId(tenantId)
			     .clientId(clientId)
			     .clientSecret(clientSecret)
			     .build();
		SecretClient secretClient = new SecretClientBuilder()
			    .vaultUrl(kvUrl)
			    .credential(clientSecretCredential)
			    .buildClient();
		
		String uri = secretClient.getSecret("cosmosuri").getValue();
		String key = secretClient.getSecret("cosmoskey").getValue();
		database = secretClient.getSecret("cosmosdb").getValue();
		
        this.azureKeyCredential = new AzureKeyCredential(key);
        DirectConnectionConfig directConnectionConfig = new DirectConnectionConfig();
        GatewayConnectionConfig gatewayConnectionConfig = new GatewayConnectionConfig();
        log.info("Values of azureKeyCredential: {}", this.azureKeyCredential);
		log.info("Values of tenantId: {}, ====== clientId:{} ", tenantId, clientId);
		
        return new CosmosClientBuilder()
            .endpoint(uri)
            .credential(azureKeyCredential)
            .directMode(directConnectionConfig, gatewayConnectionConfig)
            .clientTelemetryConfig(
                new CosmosClientTelemetryConfig()
                    .diagnosticsHandler(CosmosDiagnosticsHandler.DEFAULT_LOGGING_HANDLER));
    }
	
	@Override
    public CosmosConfig cosmosConfig() {
        return CosmosConfig.builder().build();
    }
	
	@Override
	protected String getDatabaseName() {
		return this.database;
	}
}
