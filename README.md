# 🎮 Game API

This project is a small **Spring Boot application** that manages games and players, including features like favorite games.  
It also Uses **OpenAPI Generator to automatically create the API controllers** and model classes from the OpenAPI specification. 

---

## 🧱 Tech Stack

- **Java 17**
- **Spring Boot 3.5.6**
- **Spring Data JPA (Hibernate)**
- **H2 Database (file mode)**
- **OpenAPI Generator**
- **Docker**
---

###  📂 Project Structure
```
src/
 ├─ main/
 │   ├─ java/com/comeon/ernesto/game/
 │   │   ├─ controller/      # REST controllers
 │   │   ├─ service/         # Service layer
 │   │   ├─ repository/      # Spring Data JPA repositories
 │   │   ├─ model/           # Entities and DTOs
 │   │   └─ GameApiApplication.java
 │   └─ resources/
 │       ├─ application.yml
 │       ├─ openapi.yml  # open api generator file
 │       ├─ data.sql
 └─ test/                    # Unit tests (service and controller)
 │
postman/                     # postman collection
 │
dockerfile                   #dockerfile to run on docker
```


## 🚀 How to Run the App (Local)
### 1️⃣ Clone repo
```bash
git clone https://github.com/ernestogalarza/game-love-api.git
cd game-api 
```

### 2️⃣ Run with maven
```bash
./mvnw clean install
./mvnw spring-boot:run
```


## 🐳 Run with Docker
### 1️⃣ Build the image
```bash
docker build -t game-api .
```
### 2️⃣ Run the container
```bash
docker run -p 8080:8080 game-api
```
### 🧪 Example API Endpoints

| Method | Endpoint                     | Description                    |
|--------|-----------------------------|--------------------------------|
| POST   | /player                     | Register a new player          |
| POST   | /game                       | Register a new game            |
| POST   | /game/like                  | Like a game                    |
| POST   | /game/dislike               | Dislike a game                 |
| GET    | /game/fetch                 | Get a list of games            |
| GET    | /game/favorites/player/{id} | Get favorite games of a player |
| GET    | /game/favorites/top         | Get top favorite games         |

###  ⚙ Notes
Likewise, in the project you’ll find a Postman collection to run tests on the endpoints.