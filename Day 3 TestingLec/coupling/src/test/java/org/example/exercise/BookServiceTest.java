package org.example.exercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void testBorrowBook_Success() {
        Book book = new Book("1", "Title", "Author");
        when(bookRepository.findById("1")).thenReturn(book);

        Book borrowedBook = bookService.borrowBook("1");

        assertFalse(borrowedBook.isAvailable());
        verify(bookRepository).save(book);
    }

    @Test
    void testBorrowBook_NotFound() {
        when(bookRepository.findById("2")).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            bookService.borrowBook("2")
        );
        assertEquals("Book not found", exception.getMessage());
    }

    @Test
    void testBorrowBook_AlreadyBorrowed() {
        Book book = new Book("3", "Title", "Author");
        book.setAvailable(false);
        when(bookRepository.findById("3")).thenReturn(book);

        Exception exception = assertThrows(IllegalStateException.class, () ->
            bookService.borrowBook("3")
        );
        assertEquals("Book is already borrowed", exception.getMessage());
    }

    @Test
    void testReturnBook_Success() {
        Book book = new Book("4", "Title", "Author");
        book.setAvailable(false);
        when(bookRepository.findById("4")).thenReturn(book);

        Book returnedBook = bookService.returnBook("4");

        assertTrue(returnedBook.isAvailable());
        verify(bookRepository).save(book);
    }

    @Test
    void testReturnBook_NotFound() {
        when(bookRepository.findById("5")).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            bookService.returnBook("5")
        );
        assertEquals("Book not found", exception.getMessage());
    }

    @Test
    void testReturnBook_AlreadyAvailable() {
        Book book = new Book("6", "Title", "Author");
        book.setAvailable(true);
        when(bookRepository.findById("6")).thenReturn(book);

        Exception exception = assertThrows(IllegalStateException.class, () ->
            bookService.returnBook("6")
        );
        assertEquals("Book was not borrowed", exception.getMessage());
    }

    @Test
    void testFindAvailableBooksByAuthor_Found() {
        Book book1 = new Book("7", "Title1", "AuthorA");
        Book book2 = new Book("8", "Title2", "AuthorB");
        Book book3 = new Book("9", "Title3", "AuthorA");

        when(bookRepository.findAllAvailableBooks()).thenReturn(Arrays.asList(book1, book2, book3));

        List<Book> result = bookService.findAvailableBooksByAuthor("AuthorA");

        assertEquals(2, result.size());
        assertTrue(result.contains(book1));
        assertTrue(result.contains(book3));
    }

    @Test
    void testFindAvailableBooksByAuthor_NoneFound() {
        when(bookRepository.findAllAvailableBooks()).thenReturn(Collections.emptyList());

        List<Book> result = bookService.findAvailableBooksByAuthor("AuthorZ");

        assertTrue(result.isEmpty());
    }
}
