package br.com.fiap.pedidos_sd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PedidosSdApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidosSdApplication.class, args);
	}

}
