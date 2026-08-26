package com.github.cerealequileurre.bibliotheque.service;

import com.github.cerealequileurre.bibliotheque.dto.BookCreationDTO;
import com.github.cerealequileurre.bibliotheque.entity.Book;
import com.github.cerealequileurre.bibliotheque.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(BookCreationDTO dto) {
        Book book = new Book();
        book.setTitre(dto.titre());
        book.setAuteur(dto.auteur());
        book.setIsbn(dto.isbn());
        book.setDateLecture(dto.dateLecture());
        book.setNote(dto.note());

        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}