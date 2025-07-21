import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Java
package org.example.exercise;


class BookTest {

   @Test
   void getId() {
      Book book = new Book("1", "Test Title", "Test Author");
      assertEquals("1", book.getId());
   }

   @Test
   void getTitle() {
      Book book = new Book("2", "Another Title", "Author Name");
      assertEquals("Another Title", book.getTitle());
   }

   @Test
   void getAuthor() {
      Book book = new Book("3", "Book Title", "Author X");
      assertEquals("Author X", book.getAuthor());
   }

   @Test
   void isAvailable() {
      Book book = new Book("4", "Title", "Author");
      assertTrue(book.isAvailable());
   }

   @Test
   void setAvailable() {
      Book book = new Book("5", "Title", "Author");
      book.setAvailable(false);
      assertFalse(book.isAvailable());
      book.setAvailable(true);
      assertTrue(book.isAvailable());
   }
}