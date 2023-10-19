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
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository) {
		return (args) -> {
			// save a couple of customers
			Player player1 = new Player("partymix69a@hotmail.com");
			Player player2 = new Player("jonathan.quiles@outlook.es");
			Player player3 = new Player("jonatan.quiles@gmail.com");
			Player player4 = new Player("jessibcn@gmail.com");
			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);

			// Create three games
			Game game1 = new Game(0); // Assuming you have a Game class
			Game game2 = new Game(3600);
			Game game3 = new Game(7200);


			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);

			//create game players 2 x game
			GamePlayer gamePlayer1 = new GamePlayer(0, game1, player1);
			GamePlayer gamePlayer2 = new GamePlayer(30, game1, player2);
			GamePlayer gamePlayer3 = new GamePlayer(1000, game2, player1);
			GamePlayer gamePlayer4 = new GamePlayer(0, game2, player2);
			GamePlayer gamePlayer5 = new GamePlayer(450, game3, player3);
			GamePlayer gamePlayer6 = new GamePlayer(0, game3, player4);

			gamePlayerRepository.save(gamePlayer1);
			gamePlayerRepository.save(gamePlayer2);
			gamePlayerRepository.save(gamePlayer3);
			gamePlayerRepository.save(gamePlayer4);
			gamePlayerRepository.save(gamePlayer5);
			gamePlayerRepository.save(gamePlayer6);


		};
	}

}
