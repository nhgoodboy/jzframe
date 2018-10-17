package com.musikouyi.jzframe.config;

import javafx.scene.NodeBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;

//@Component
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.musikouyi.jzframe.search")
public class ElasticSearchConfig {

//    @Bean
//    public Client client() {
//        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch")
//                .put("client.transport.ignore_cluster_name", false).build();
//        TransportClient client = new TransportClient(settings);
//        TransportAddress adress = new InetSocketTransportAddress("127.0.0.1", 9300);
//        client.addTransportAddress(adress);
//        return client;
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() {
//        return new ElasticsearchTemplate(client());
//    }
}
