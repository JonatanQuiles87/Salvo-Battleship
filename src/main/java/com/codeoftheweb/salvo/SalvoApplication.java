package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository) {
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
			GamePlayer gamePlayer3 = new GamePlayer(1000, game2, player2);
			GamePlayer gamePlayer4 = new GamePlayer(0, game2, player4);
			GamePlayer gamePlayer5 = new GamePlayer(450, game3, player3);
			GamePlayer gamePlayer6 = new GamePlayer(0, game3, player4);

			gamePlayerRepository.save(gamePlayer1);
			gamePlayerRepository.save(gamePlayer2);
			gamePlayerRepository.save(gamePlayer3);
			gamePlayerRepository.save(gamePlayer4);
			gamePlayerRepository.save(gamePlayer5);
			gamePlayerRepository.save(gamePlayer6);

			List<String> shipLocation1 = Arrays.asList("H2","H3","H4");
			Ship ship1 = new Ship("Destroyer", gamePlayer1, shipLocation1);
			shipRepository.save(ship1);
			List<String> shipLocation2 = Arrays.asList("E1","F1","G1");
			Ship ship2 = new Ship("Submarine", gamePlayer1, shipLocation2);
			shipRepository.save(ship2);
			List<String> shipLocation3 = Arrays.asList("B4","B5");
			Ship ship3 = new Ship("Patrol Boat", gamePlayer1, shipLocation3);
			shipRepository.save(ship3);
			List<String> shipLocation4 = Arrays.asList("B5","C5","D5");
			Ship ship4 = new Ship("Destroyer", gamePlayer2, shipLocation4);
			shipRepository.save(ship4);
			List<String> shipLocation5 = Arrays.asList("F1","F2");
			Ship ship5 = new Ship("Patrol Boat", gamePlayer2, shipLocation5);
			shipRepository.save(ship5);
			List<String> shipLocation6 = Arrays.asList("B5","C5","D5");
			Ship ship6 = new Ship("Destroyer", gamePlayer3, shipLocation6);
			shipRepository.save(ship6);
			List<String> shipLocation7 = Arrays.asList("C6","C7");
			Ship ship7 = new Ship("Patrol Boat", gamePlayer3, shipLocation7);
			shipRepository.save(ship7);
			List<String> shipLocation8 = Arrays.asList("A2","A3","A4");
			Ship ship8 = new Ship("Submarine", gamePlayer4, shipLocation8);
			shipRepository.save(ship8);
			List<String> shipLocation9 = Arrays.asList("G6","H6");
			Ship ship9 = new Ship("Patrol Boat", gamePlayer4, shipLocation9);
			shipRepository.save(ship9);
			List<String> shipLocation10 = Arrays.asList("B5","C5","D5");
			Ship ship10 = new Ship("Destroyer", gamePlayer5, shipLocation10);
			shipRepository.save(ship10);
			List<String> shipLocation11 = Arrays.asList("C6","C7");
			Ship ship11 = new Ship("Patrol Boat", gamePlayer5, shipLocation11);
			shipRepository.save(ship11);
			List<String> shipLocation12 = Arrays.asList("A2","A3","A4");
			Ship ship12 = new Ship("Submarine", gamePlayer6, shipLocation12);
			shipRepository.save(ship12);
			List<String> shipLocation13 = Arrays.asList("G6","H6");
			Ship ship13 = new Ship("Patrol Boat", gamePlayer6, shipLocation13);
			shipRepository.save(ship13);





		};
	}

}
