# game-online
[![Build Status](https://travis-ci.com/lukago/game-online.svg?token=tsv9JMKpCY8piyBJajzn&branch=develop)](https://travis-ci.com/lukago/game-online)

Online game application.

# Installation 
1. Install postgreSQL and Node.js
2. Create local database in postgres:
    1. name: gamedb, login: postgres, password is blank   
3. go to client directory and run npm install

# Run
1. Build project with maven clean install
2. Start application with IDE or mvn spring-boot:run
3. Go to localhost:8080
4. localhost:8080/swagger-ui.html - swagger API tests
5. localhost:8080/login - login page
 