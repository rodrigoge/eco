## Eco 🌳

🗑️ Helping you dispose of waste the right way. 



### Technologies and stack used

**Back-end:** 
- Java 17
- Maven
- Spring Boot
- Spring Framework (Spring Data JPA, Spring Security)
- PostgreSQL
- Flyway Migration
- Lombok

**Front-end:**
- React.js
- Vite.js



### How to install

Click [here](git@github.com:rodrigoge/ecopoints.git) to download the project, and follow instructions:

```bash
  mvn clean install
  mvn clean package
  java -jar target/userservice-1.0.0.0.jar
```
    
### API Documentation

#### User Requests

```http
  GET    /users - Return a users list
  POST   /users/register - Create a new user
  PATCH  /users/{userId} - Update one or more fields of user
  DELETE /users/{userId} - Delete a user 
```

#### Eco Requests

```http
  GET    /points - Return a points list
  POST   /points/register - Create a new point
  PATCH  /points/{pointId} - Update one or more fields of point
  DELETE /points/{pointId} - Delete a point 
```

#### State Requests

```http
  GET    /states - Return a states list
```



### Environment Variables

`db_url` - Url used to connect to database

`db_schema` - Schema name referring the database used

`db_user` - User name for connection

`db_password` - Password for connection

`port` - Port on which the application will run

### Author

- [@rodrigoge](https://www.github.com/rodrigoge)


### Licença

[MIT](https://github.com/rodrigoge/eco/blob/main/LICENSE)

