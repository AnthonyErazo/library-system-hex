package com.example.bibliotecahex.infrastructure.out.persistence.repository.jpa;

import com.example.bibliotecahex.infrastructure.out.persistence.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepositoryJPA extends JpaRepository<BookEntity, String> {

    Optional<BookEntity> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
}
