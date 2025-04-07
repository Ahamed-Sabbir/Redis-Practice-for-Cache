# Redis Practice with Spring Boot

This project demonstrates how to use **Spring Boot Cache** with **Redis** as a cache store.  
It supports basic CRUD operations on a `User` entity, and caches user data for faster access.

## Tech Stack
- **Spring Boot** 3.4.4
- **Spring Data JPA**
- **Spring Cache (with Redis)**
- **Redis** (via Docker)
- **H2 In-Memory Database**
- **Lombok**
- **Gradle**
- ([https://app.warp.dev/block/R8iykeai0iBdSPbqpfLQ1q])

---

## How to Run

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Ahamed-Sabbir/Redis-Practice-for-Cache.git
   ```

2. **Run Redis using Docker:**
   ```bash
   docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
   ```

3. **Run the Spring Boot Application:**
   ```bash
   ./gradlew bootRun
   ```

4. **Access H2 Console (optional, for DB debugging):**
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:redis-practice-db`
   - Username: `root`
   - Password: `root`

---

## API Documentation

### 1. **Get User by ID**
- **Endpoint:** `GET /users/{id}`
- **Description:** Fetch a user by their ID.
- **Sample Response:**
  ```json
  {
    "user": {
      "name": "samia",
      "email": "samia@gmail.com",
      "id": 1
    }
  }
  ```
- **Notes:** The result will be cached in Redis.
- ```bash {"@class":"com.sabbir.model.User","name":"samia","email":"samia@gmail.com","id":1} ```

---

### 2. **Get User by Email**
- **Endpoint:** `GET /users/email/{email}`
- **Description:** Fetch a user by their email.
- **Sample Response:**
  ```json
  {
    "user": {
      "name": "samia",
      "email": "samia@gmail.com",
      "id": 1
    }
  }
  ```
- **Notes:** The result will be cached in Redis.

---

### 3. **Save a New User**
- **Endpoint:** `POST /users`
- **Description:** Save a new user into the database and update the cache.
- **Request Body:**
  ```json
  {
    "name": "samia",
    "email": "samia@gmail.com"
  }
  ```
- **Sample Response:**
  ```json
  {
    "user": {
      "name": "samia",
      "email": "samia@gmail.com",
      "id": 1
    },
    "message": "user saved"
  }
  ```
- **Notes:** Cache will be updated with the new user.

---

### 4. **Delete User**
- **Endpoint:** `DELETE /users/{id}`
- **Description:** Delete a user by their ID and remove them from the cache.
- **Sample Response:**
  ```json
  {
    "message": "user removed"
  }
  ```
- **If User Not Found:**
  ```json
  {
    "user": "User not found"
  }
  ```

---

## Important Details

- **Caching Behavior:**
  - Users are cached when fetched by ID or email.
  - Cached only if the result is not `null`.
  - Cache is updated when a user is saved.
  - Cache is evicted when a user is deleted.
  
- **Handling Non-Existent Users:**
  - If a user does not exist, it will **not** be cached (`unless = "#result == null"` ensures this).

- **Redis Docker Image Used:**
  - [`redis/redis-stack:latest`](https://hub.docker.com/r/redis/redis-stack)

---

## Project Structure
```
src/main/java/com/sabbir
|
├── config
│    └── RedisConfig.java
│
├── controller
│    └── UserController.java
│
├── model
│    └── User.java
│
├── repository
│    └── UserRepo.java
│
├── service
│    └── UserService.java
```

---

## Build and Dependency Management
- **Build tool:** Gradle
- **Main Dependencies:**
  - `spring-boot-starter-web`
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-cache`
  - `spring-boot-starter-data-redis`
  - `h2`
  - `lombok`
  - `https://app.warp.dev/block/R8iykeai0iBdSPbqpfLQ1q`

---

## Author
**Sabbir**
