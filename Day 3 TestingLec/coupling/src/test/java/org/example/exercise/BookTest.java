package org.example.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class BookTest {

    @Test
    void testConstructorAndGetters() {
        Book book = new Book("B001", "1984", "George Orwell");

        assertEquals("B001", book.getId(), "Book ID should match");
        assertEquals("1984", book.getTitle(), "Book title should match");
        assertEquals("George Orwell", book.getAuthor(), "Book author should match");
        assertTrue(book.isAvailable(), "Book should be available by default");
    }

    @Test
    void testSetAvailableTrue() {
        Book book = new Book("B002", "Brave New World", "Aldous Huxley");
        book.setAvailable(true);
        assertTrue(book.isAvailable(), "Book should be available after setting to true");
    }

    @Test
    void testSetAvailableFalse() {
        Book book = new Book("B003", "Fahrenheit 451", "Ray Bradbury");
        book.setAvailable(false);
        assertFalse(book.isAvailable(), "Book should not be available after setting to false");
    }

    @Test
    void testMultipleBooksIndependence() {
        Book book1 = new Book("B004", "The Hobbit", "J.R.R. Tolkien");
        Book book2 = new Book("B005", "Dune", "Frank Herbert");

        book1.setAvailable(false);

        assertFalse(book1.isAvailable(), "Book1 availability should be false");
        assertTrue(book2.isAvailable(), "Book2 should remain available");
    }
}
