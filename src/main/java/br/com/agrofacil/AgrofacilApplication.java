
package br.com.agrofacil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "AgroFácil - API de Gestão Agrícola", version = "1.0.0", description = "API REST para gerenciamento de agricultores, plantações e colheitas. Desenvolvida com Java, Spring Boot, PostgreSQL e Flyway para o projeto acadêmico de SOA."))
public class AgrofacilApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgrofacilApplication.class, args);
	}

}
