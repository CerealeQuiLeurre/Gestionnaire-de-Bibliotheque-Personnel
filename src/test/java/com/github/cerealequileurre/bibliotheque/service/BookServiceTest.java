package com.github.cerealequileurre.bibliotheque.service;

import com.github.cerealequileurre.bibliotheque.dto.BookCreationDTO;
import com.github.cerealequileurre.bibliotheque.entity.Book;
import com.github.cerealequileurre.bibliotheque.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void createBook_ShouldSaveAndReturnBook() {
        BookCreationDTO dto = new BookCreationDTO("Dune", "Frank Herbert", "978-2266155489", LocalDate.now(), 5);
        Book book = new Book();
        book.setId(1L);
        book.setTitre(dto.titre());
        book.setAuteur(dto.auteur());
        book.setIsbn(dto.isbn());
        book.setDateLecture(dto.dateLecture());
        book.setNote(dto.note());

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.createBook(dto);

        assertNotNull(result);
        assertEquals("Dune", result.getTitre());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void getAllBooks_ShouldReturnListOfBooks() {
        Book book = new Book();
        book.setId(1L);
        book.setTitre("Dune");

        when(bookRepository.findAll()).thenReturn(List.of(book));

        List<Book> books = bookService.getAllBooks();

        assertEquals(1, books.size());
        assertEquals("Dune", books.get(0).getTitre());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookById_WhenFound_ShouldReturnBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitre("Dune");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals("Dune", result.getTitre());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void getBookById_WhenNotFound_ShouldThrowException() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.getBookById(99L));
        verify(bookRepository, times(1)).findById(99L);
    }

    @Test
    void updateBook_WhenFound_ShouldUpdateAndReturnBook() {
        Book existingBook = new Book();
        existingBook.setId(1L);
        existingBook.setTitre("Ancien Titre");

        BookCreationDTO updateDto = new BookCreationDTO("Nouveau Titre", "Auteur", "978-2266155489", LocalDate.now(),
                4);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Book result = bookService.updateBook(1L, updateDto);

        assertNotNull(result);
        assertEquals("Nouveau Titre", result.getTitre());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(existingBook);
    }

    @Test
    void deleteBook_ShouldCallDelete() {
        Book book = new Book();
        book.setId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).delete(book);
    }
}