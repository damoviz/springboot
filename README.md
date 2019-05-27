# springboot
###### REST API para crear Clientes y Tarjetas de credito

### Prerequisitos
- JDK 1.8+  
- creamos la imagen de postgres -> docker pull postgres:9.6.6-alpine
- contenedor name postgres -> docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=damoviz postgres:9.6.6-alpine

### Tecnologias stack:
* Spring Boot
* Spring Data JPA
* Spring Rest
* Postgres
* Swagger -> Para ver la documentacion de los servicios (http://localhost:8080/swagger-ui.html)
* Lombox (Nos permite eliminar codigo repetitivo "Getters","Setters")
* Docker

### CRUD services:
 - [UserLoginResource](https://github.com/damoviz/springboot/blob/master/src/main/java/com/damoviz/springbootpeiky/resources/UserLoginResource.java#L32) 
 - [ClienteResource](https://github.com/damoviz/springboot/blob/master/src/main/java/com/damoviz/springbootpeiky/resources/ClienteResource.java#L36)  
 -[CreditCardResource]https://github.com/damoviz/springboot/blob/master/src/main/java/com/damoviz/springbootpeiky/resources/CreditCardResource.java#L35)  

### IDE'S:
* Spring tool suite
* OmniDB
* Postman
