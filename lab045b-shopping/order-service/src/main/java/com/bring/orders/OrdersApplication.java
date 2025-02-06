package com.bring.orders;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class OrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}

	// Define KafkaAdmin bean
	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		return new KafkaAdmin(configs);
	}

	@Bean
	public NewTopic topic(KafkaAdmin kafkaAdmin) {
		// Create an AdminClient to delete the topic
		try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
			// Delete the topic if it exists
			DeleteTopicsResult deleteResult = adminClient.deleteTopics(Collections.singletonList("notification-topic"));
			deleteResult.all().get(); // Wait for the deletion to complete
			System.out.println("Deleted topic: notification-topic");
		} catch (ExecutionException | InterruptedException e) {
			System.out.println("Topic does not exist or could not be deleted: " + e.getMessage());
		}

		System.out.println("Creating a topic on Kafka");
		return TopicBuilder.name("notification-topic")
				.partitions(10)
				.replicas(1)
				.build();
	}
}