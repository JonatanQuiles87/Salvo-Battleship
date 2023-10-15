package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository) {
		return (args) -> {
			// save a couple of customers
			Player player1 = new Player("partymix69a@hotmail.com");
			Player player2 = new Player("jonathan.quiles@outlook.es");
			Player player3 = new Player("jessibcn@gmail.com");
			Player player4 = new Player("oriol.furnells@gmail.com");
			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);

			// Create three games
			Game game1 = new Game(); // Assuming you have a Game class
			Game game2 = new Game();
			Game game3 = new Game();

			// Set the dates of the second and third games to one hour and two hours later
			LocalDateTime now = LocalDateTime.now();
			game1.setDateInitial(now);
			game2.setDateInitial(now.plusHours(1));
			game3.setDateInitial(now.plusHours(2));

			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);
		};
	}

}
