# FiveChan
___

## Integrantes
- Fabricio Jesús Huaquisto Quispe
- Erick Malcoaccha Diaz
- Marko Sumire Ramos
- Christian Taipe Saraza
- Alonso Chullunquia Rosas
- Sergio Castillo



#########################################
## Christian Daniel Taipe Saraza
## LABORATORIO 10
### 1. Principios SOLID
- Single Responsibility Principle (SRP): Cada clase 
debe tener una única responsabilidad.
- Open/Closed Principle (OCP): Las entidades de software deben estar abiertas para 
la extensión, pero cerradas para la modificación.
- Liskov Substitution Principle (LSP): Los objetos de una superclase 
deben poder ser reemplazados con objetos de una subclase sin afectar el funcionamiento del programa.
- Interface Segregation Principle (ISP): Los clientes no deben estar 
obligados a depender de interfaces que no utilizan.
- Dependency Inversion Principle (DIP): Las dependencias deben 
abstraerse y no depender de implementaciones concretas.

### 2. Clean Arquitecture:
Separaremos nuestro código en capas de manera que las capas de dominio y aplicación 
no dependan de las capas de infraestructura.


**Ejemplo de SRP, OCP, LSP, ISP, DIP en `CommentService.java`:**
```java
package com.fivechan.forum.context.comment.application;

import com.fivechan.forum.context.comment.domain.Comment;
import com.fivechan.forum.context.comment.domain.CommentRepository;
import com.fivechan.forum.context.comment.domain.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void createComment(UUID userId, UUID topicId, String content) {
        Comment comment = new Comment(userId, topicId, content);
        this.commentRepository.save(comment);
    }

    public void deleteComment(UUID commentId) {
        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(MessageFormat.format("Comment not found with id: {0}", commentId)));
        this.commentRepository.delete(comment);
    }

    public void editComment(UUID commentId, String newContent) {
        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));
        comment.setContent(newContent);
        this.commentRepository.update(comment);
    }

    public List<Comment> getAllComments() {
        return this.commentRepository.findAll();
    }

    public List<Comment> getCommentsByUserId(UUID userId) {
        return this.commentRepository.findByUserId(userId);
    }

    public Comment getCommentById(UUID commentId) {
        return this.commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));
    }

    public List<CommentDTO> getCommentsByTopicId(UUID topicId) {
        return this.commentRepository.findByTopicId(topicId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CommentDTO convertToDto(Comment comment) {
        return new CommentDTO(comment.getUserId(), comment.getTopicId(), comment.getContent());
    }

}
```
## LABORATORIO 11
### 1. Estilos de Programación.
- **Pipeline: Para procesar datos en una secuencia de pasos.**
- **Error/Exception Handling: Manejo robusto de errores y excepciones.**
- **Restful: Uso de principios REST para servicios web.**

**Ejemplo de 'Pipeline' en `getCommentsByTopicId.java` donde usamos `stream()` y `map()`:**
```java
public List<CommentDTO> getCommentsByTopicId(UUID topicId) {
        return this.commentRepository.findByTopicId(topicId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
```
**Ejemplo de 'Error/Exception Handling' en `findById.java` donde se realiza el manejo de excepciones:**
```java
@Override
public Optional<Comment> findById(UUID id) {
    CommentEntity entity = entityManager.find(CommentEntity.class, id);
    return Optional.ofNullable(entity != null ? entity.toDomain() : null);
}
```
**Ejemplo de 'Resful' en `JpaCommentRepository.java` para el diseño del servicio con métodos para operaciones CRUD:**
```java
package com.fivechan.forum.context.comment.infrastructure;

import com.fivechan.forum.context.comment.domain.Comment;
import com.fivechan.forum.context.comment.domain.CommentEntity;
import com.fivechan.forum.context.comment.domain.CommentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaCommentRepository implements CommentRepository {
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @Transactional
    public void save(Comment comment) {
        CommentEntity commentEntity = CommentEntity.fromDomain(comment);
        entityManager.persist(commentEntity);
    }

    @Override
    @Transactional
    public void update(Comment comment) {
        CommentEntity commentEntity = CommentEntity.fromDomain(comment);
        entityManager.merge(commentEntity);
    }

    @Override
    @Transactional
    public void delete(Comment comment) {
        CommentEntity commentEntity = CommentEntity.fromDomain(comment);
        entityManager.remove(commentEntity);
    }

    @Override
    public Optional<Comment> findById(UUID id) {
        CommentEntity entity = entityManager.find(CommentEntity.class, id);
        return Optional.ofNullable(entity != null ? entity.toDomain() : null);
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<CommentEntity> query = entityManager.createQuery("FROM CommentEntity", CommentEntity.class);
        return query.getResultList().stream()
                .map(CommentEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findByUserId(UUID userId) {
        TypedQuery<CommentEntity> query = entityManager.createQuery(
                "SELECT c FROM CommentEntity c WHERE c.userId = :userId", CommentEntity.class);
        query.setParameter("userId", userId);
        return query.getResultList().stream()
                .map(CommentEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findByTopicId(UUID topicId) {
        TypedQuery<CommentEntity> query = entityManager.createQuery(
                "SELECT c FROM CommentEntity c WHERE c.topicId = :topicId", CommentEntity.class);
        query.setParameter("topicId", topicId);
        return query.getResultList().stream()
                .map(CommentEntity::toDomain)
                .collect(Collectors.toList());
    }
}

```

