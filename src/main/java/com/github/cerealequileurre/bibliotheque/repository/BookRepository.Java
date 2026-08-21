package com.github.cerealequileurre.bibliotheque.repository;

import com.github.cerealequileurre.bibliotheque.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}