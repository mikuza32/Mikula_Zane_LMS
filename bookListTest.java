import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class bookListTest {



    @Test
    void addBooks() {
        bookList list = new bookList();

        list.addBooks("33453", "The Sopranos", "David Chase");

        assertTrue(list.containsBook("The Sopranos"));
    }



    @Test
    void testDeleteBooksID() {
        bookList list = new bookList();

        list.addBooks("43434", "Documentary", "ABC");

        list.deleteBooksID("43434");

        assertFalse(list.containsBook("Documentary"));


    }

    @Test
    void testDeleteBooksTitle() {
        bookList list = new bookList();

        list.addBooks("34234", "How to Code", "Professor Walauskis");

        list.deleteBooksTitle("How to code");

        assertFalse(list.containsBook("How to code"));

    }

    @Test
    void testCheckOutBooksTitle() {
        bookList list = new bookList();

        list.addBooks("23234", "Harry Potter", "JK Rowling");

        list.checkOutBooksTitle("Harry Potter");

        assertTrue(list.containsBook("Harry Potter"));




    }

    @Test
    void testCheckInBooksTitle() {
        bookList list = new bookList();

        list.addBooks("54724", "Harry Potter", "JK Rowling");

        list.checkInBooksTitle("Harry Potter");

        assertTrue(list.containsBook("Harry Potter"));

    }
}