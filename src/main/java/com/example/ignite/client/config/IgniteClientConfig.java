package com.example.ignite.client.config;


import org.apache.ignite.Ignition;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IgniteClientConfig {

    @Bean
    public IgniteClient igniteClient() {
        // Thin Client configuration
        ClientConfiguration clientConfiguration = new ClientConfiguration()
                .setAddresses("127.0.0.1:10800"); // Replace with your Ignite server node address
        return Ignition.startClient(clientConfiguration);
    }
}
