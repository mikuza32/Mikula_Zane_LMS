// Zane Mikula CEN-3024C-24668
// bookList class
// This class is used for creating the arrayList that stores all successful book entries. As well as each method that is expressed per each case in the >
// main class. The bookInfo arraylist is created and set by the bookList method. Then each method, addBooks, deleteBooks, listBooks is called per the users input.
import java.util.ArrayList; //imports the java utility array list in order to create an array
public class bookList {
    private ArrayList<bookInfo> books; //privates the arraylist created and is called as books

    public bookList() {
        this.books = new ArrayList<>(); //sets the arraylist and so it can be called as books in addBooks, deleteBooks, and listBooks methods; holds all books created as well

    }

    public void addBooks(String bookID, String bookTitle, String bookAuthor) { //addBooks method created for option 1 in the menu, manipulates such objects
        bookInfo newBook = new bookInfo(bookID, bookTitle, bookAuthor);
        books.add(newBook);                                                  // list.add method is used to create a new book based off of the users ID, Title, and Author inputs into the arraylist
        System.out.println("Desired book information added: " + newBook); //confirmation of addition text is printed as well as the newBook that was just created

    }

    public void deleteBooks(String bookID) { //deleteBooks method created for option 2 in the menu
        for (bookInfo book : books) {
            if (bookID.equalsIgnoreCase(bookID)) { //using the equalsignorecase method within the if statement, regardless of case the deleteBooks method will recognize if the ID is attached to a present book or not
                books.remove(book); //if there is a book with a matching ID the book will be removed entirely hence the .remove(book) method
                System.out.println("Removal completed successfully: " + book); //confirmation text that the book has been removed
                return;
            }
        }
        System.out.println("Book with the intended removal of this ID not found: " + bookID); //if there is no matching ID console prints a statement that there is no ID matching along with the ID that was input
    }
    public void listBooks() {
        if (books.isEmpty()) { //isEmpty method is used in the if statement so that if there are no books in the LMS you will be sent to the main menu while being told it is empty
            System.out.println("No books in the current LMS.");
        }
        else {
            System.out.println("Current LMS: "); //Label for all of the books currently in the LMS
            for (bookInfo book : books) {
                System.out.println(book); //for loop prints the entire bookInfo arraylist formatted from the toString created in the bookInfo class.
            }
        }
    }
}
