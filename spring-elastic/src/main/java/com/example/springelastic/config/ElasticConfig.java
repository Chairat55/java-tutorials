package com.example.springelastic.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.springelastic.repositories.els")
@ComponentScan(basePackages = { "com.example.springelastic" })
public class ElasticConfig {

    @Value("${elasticsearch.host}")
    private String elasticsearchHost;

    @Bean
    public RestHighLevelClient client() {
        ClientConfiguration clientConfiguration
                = ClientConfiguration.builder()
                .connectedTo(elasticsearchHost)
                .withSocketTimeout(30000)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}