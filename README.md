# Springboot-con-Swagger-2-Actuator-y-MongoDB
Demo que utiliza Springboot con Swagger 2 para documentar un API REST

# Contenido de la aplicación
La configuracion inicial en el Application con los Beans para iniciar Swagger

Un Modelo Persona
Un Controller para las operaicones del API y un Servicio para contar las metricas para Actuator


# URL de uso

http://localhost:2222/swagger-ui.html
http://localhost:2222/actuator/


# Configuracion del application.yml
server:  
  port: ${port:2222}

spring:  
  application:
    name: first-service
    
logging:
  pattern:
    console: "%d{HH:mm:ss.SSS} %-5level %logger{36} - %msg%n"
    file: "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"

  file: app.log
  
management:
  security:
    enabled: false
  
  endpoints:
    web:
      exposure:
        include: "*"
  health:
    mongo:
      enabled: true
