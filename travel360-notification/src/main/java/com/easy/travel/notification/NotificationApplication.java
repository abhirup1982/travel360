package com.easy.travel.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ScheduledThreadPoolExecutor;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}

	@Bean(name = "fireBase")
	public RestTemplate firebaseRestTemplate() {
	    return new RestTemplate();
    }

    @Bean(name = "loadBalance")
	@LoadBalanced
    public RestTemplate loadRestTemplate() { return new RestTemplate();}

	@Bean
	public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor() {
		ScheduledThreadPoolExecutor threadPoolTaskExecutor = new ScheduledThreadPoolExecutor(5);
		return threadPoolTaskExecutor;
	}
}
