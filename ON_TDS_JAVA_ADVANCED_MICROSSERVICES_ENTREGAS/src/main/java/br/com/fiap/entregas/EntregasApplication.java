package br.com.fiap.entregas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EntregasApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntregasApplication.class, args);
	}

}
