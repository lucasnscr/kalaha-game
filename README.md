# Kalah Game

This project has the purpose to run kalaha game with two players on the same computer.

## Rules

### **Game Play**
The player who begins with the first move picks up all the stones in any of his own six pits, and sows the stones on to the right, one in each of the following pits, including his own big pit. No stones are put in the opponents' big pit. If the player's last stone lands in his own big pit, he gets another turn. This can be repeated several times before it's the other player's turn.

### **Capturing Stones**
During the game the pits are emptied on both sides. Always when the last stone lands in an own empty pit, the player captures his own stone and all stones in the opposite pit (the other player’s pit) and puts them in his own (big or little?) pit.

### **The Game Ends**
The game is over as soon as one of the sides runs out of stones. The player who still has stones in his pits keeps them and puts them in his big pit. The winner of the game is the player who has the most stones in his big pit.

### Installation and Technologies

- Docker 
- Docker-compose
- Java 17
- Maven
- SpringBoot
- SpringDoc OpenApi
- Lombok
- JUNIT 5
- Docker Maven Plugin by Fabric8 for generate containers with ARM64 Architecture

### About Running Project 
For running this project you need two forms, Maven or Docker-Compose. For using maven you need run this command
```
mvn spring-boot:run
```

**Docker Compose**

If you prefer using docker-compose, you need run the container, enter in container bash and execute the commnands:

```
docker-compose up -d
```

OpenApi documentation: <http://localhost:8081/doc>

### Running the tests

```
mvn test
```

### About the Solution



## Author

* [Lucas Nascimento](https://github.com/lucasnscr)


