package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository) {
		return (args) -> {
			// save a couple of customers
			playerRepository.save(new Player("partymix69a@hotmail.com"));
			playerRepository.save(new Player("jonathan.quiles@outlook.es"));
			playerRepository.save(new Player("jessibcn@gmail.com"));
			playerRepository.save(new Player("oriol.furnells@gmail.com"));
		};
	}

}
