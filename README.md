
# Salvo Battleship Web Application

This is a full-stack web application of Salvo Game.
This project is created using JavaScript on the frontend side and Spring Boot framework is used for the Java backend side based on RESTful web server.
Application also uses H2 database for relationship database.

## Prerequisites for installation

You need a diferent tools you need install in order to run this application:

· Java 11

· An IDE (I've used Intellij IDEA), Visual Studio Code may produce bugs without apropiate extensions.

· Git

## Getting Started

These instructions will get you a copy of the project up and running on your local machine, for development and testing purposes.

### Installation

1. Clone the repository or download Zip in :

https://github.com/JonatanQuiles87/salvo-BattleShip for download

https://github.com/JonatanQuiles87/salvo-BattleShip.git for Clone

2. Open the cloned or download project in the IDE that you installed.

3. Start the application

·In case if you use terminal, write this command in the directory of where build.gradle:

./gradlew bootrun

or

· You can use the Intellij gradle bootRun configuration on right top corner.

The application will be accessible at localhost:8080. Go to http://localhost:8080/web/games.html to play the game.

### Rules and Usage Game

1. This game can be played by only two players.

2. You can see the list of the games and the scoreboard on /web/games.html page even if you are not logged in.

3. You have to log in / sign up to be able to view, join or create games on /web/games.html page. You might see an empty game list and scoreboard if there is no created games yet.

4. You can create your own game by clicking on 'Create New Game' button and wait for you opponent.

5. If there are some created games listed, then you can join one of the games that have only one player by clicking 'Join' button next to the game. This one player cannot be you, you cannot play against yourself.

6. You can also view the games that you are one of the players by clicking on 'View' button. Therefore, you can play more than one game at the same time.

7. When you create, join or view a game, you will be directed to /web/game.html?gp= page.

8. If you are the only player in the game, then you have to wait for your opponent to play. You can place and save your ships meanwhile though.

9. Select a ship and a direction on the left menu and place it on the 'Ship Grid' map. The ships placed on the map will be shown in the list of 'Placed Ship' on the left bottom side.

10. You can either save(sending the ship data to database so no change can be done after saving) or remove the ship by using checkboxes and 'Remove' and 'Save' buttons. Removing will remove the checked ships from the map, so you can replace them.

11. After you and your opponent save all the ships, you can start making salvo.

12. The creator player can make the first salvo of each turn. If you joined to a game created by anyone else, then you have to wait for your opponent's salvo.

13. You can select at most 5 locations on the 'Salvo Grid' map at the beginning. You can select as many locations as the number of your ships that are not sunk.

14. You can send the salvo by clicking 'Fire Salvo' button on the left side.

15. If you hit any of your opponent's ships then the hit locations will be shown in purple on the 'Salvo Grid' map. Also, same for your ships on 'Ship Grid' map.

16. You can make salvo only once for each turn. When both players make their salvo, then the next turn can start. Again, the creator can fire first salvo of the next turn.

17. You can see the game history at the bottom of the page after each salvo (yours or opponent's).

18. The game can end with three possible results: Win, Lose and Tie. And the game can end if all the players played same amount of turns.

19. If you sink all of your opponent's ship, and you still have floating ship at the end of a turn, then you win. If other way around, then you lose. If both players' ships are sunk at the end of a turn, then it is tie.

20. The winner gets 1 point, the loser gets 0 point. If it is tie, both players get 0.5 point. The points are added to the scoreboard on page /web/games.html.

## Testing API points

For testing pursoses, you will been able in main file SalvoApplication, you will uncomment the grey section to activate sample data tested.

The API point for test are: 

1. http://localhost:8080/rest/games will list all the games

2. http://localhost:8080/rest/players will list all the players

3. http://localhost:8080/rest/games/1/players will list all the players in game 1

4. http://localhost:8080/api/games will return a customized JSON list of games and players.

5. http://localhost:8080/web/games.html will display a human-readable list of games and players.

6. http://localhost:8080/api/game_view/1

7. http://localhost:8080/rest/ships

8. http://localhost:8080/rest/salvoes

9. http://localhost:8080/rest/scores

It may be necessary to log in or register to access some endpoints.

# Author

Jonatan Quiles

#Email

jonatan.quiles@gmail.com
