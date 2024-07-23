# Fivechan Forum Project

Este proyecto es una implementación de un foro utilizando Spring 
Boot 3.3 y Java 21, siguiendo los principios de Domain-Driven Design (DDD) y 
Clean Architecture. En este README se destacan las buenas prácticas de codificación 
legible implementadas en el proyecto.

## Prácticas de Codificación Legible

### 1. Nombres Significativos

Los nombres de las clases, métodos y variables son claros y descriptivos 
para que el código sea autoexplicativo. Esta práctica mejora la 
legibilidad y facilita el entendimiento del código por otros 
desarrolladores.

#### Ejemplos:
- **Clases**: `CreateUser`, `CreateComment`, `CreateTopic`.
- **Métodos**: `execute` en lugar de `process` o `run`, ya que `execute` describe mejor la acción realizada.

**Ejemplo en `CreateComment.java`:**
```java
package com.fivechan.forum.context.comment.application;

public class CreateComment {
    // attributes :)
    private Long topicId;
    private Long userId;
    private String content;

    // constructor
    public CreateComment(Long topicId, Long userId, String content) {
        this.topicId = topicId;
        this.userId = userId;
        this.content = content;
    }

    // methods
    public void execute() {}

    // getters y setters :P
    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
```

### 2. Funciones Pequeñas y Comentarios Explicativos

Las funciones son pequeñas y realizan una única tarea bien definida. Además, se han 
añadido comentarios para explicar partes del código que podrían no ser 
inmediatamente obvias. Esto facilita su comprensión, prueba y 
mantenimiento.
#### Ejemplo:
- **Método execute en `CreateUser.java`:** Este método es responsable únicamente de crear un usuario y 
guardarlo en el repositorio. No realiza ninguna otra tarea, lo que 
sigue el principio de responsabilidad única.

**Ejemplo en `CreateUser.java`:**
```java
package com.fivechan.forum.context.user.application;

import com.fivechan.forum.context.user.domain.User;
import com.fivechan.forum.context.user.domain.UserRepository;

import java.util.UUID;

public class CreateUser {
    UserRepository userRepository;
    private String username;
    private String password;
    private String email;

    // constructor
    public CreateUser(UserRepository userRepository, String username, String password, String email) {
        this.userRepository = userRepository;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // methods
    public void run(UUID id, String name, String description, String avatar) {
        User user = new User(id, name, description, avatar);
        this.userRepository.save(user);
    }

    // method for the creation of user
    public void execute() {
        // generate new UUID
        UUID id = UUID.randomUUID();
        // new description for the user :)
        String description = "Default description";
        String avatar = "Default avatar";
        // create and save user
        this.run(id, this.username, description, avatar);
    }

    // getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```