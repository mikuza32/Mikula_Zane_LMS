// Zane Mikula CEN-3024C-24668
// bookList class
// This class is used for creating the arrayList that stores all successful book entries. As well as each method that is expressed per each case in the >
// main class. The bookInfo arraylist is created and set by the bookList method. Then each method, addBooks, booksFromFile, deleteBooksID, deleteBooksTitle, >
// checkOutBookstitle, checkInBooksTitle, and listBooks is called per the users input.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList; //imports the java utility array list in order to create an array
import java.util.Scanner;

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

    public void booksFromFile(String fileName) { //booksFromFile method is used to add books to the LMS from a txt file path
        try {                                    // try catch exception handling used to throw an error if the designated file path cannot be found
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file); //this method scans the file and then leads to reading and formatting the .txt file that is submitted by the user
            while (fileScanner.hasNextLine()) {      // while loop set to scan each line of books and then format
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");  // the .txt file is then differentiated by each comma (Book ID, Book Title, Book Author)
                if (parts.length == 3) {                 // each row is separated into three parts, if statement used to only format rows with three parts
                    String bookID = parts[0].trim();     // first part before comma is the bookID
                    String bookTitle = parts[1].trim();  // second part after first comma is the bookTitle
                    String bookAuthor = parts[2].trim(); // third part after second comma is the bookAuthor
                    addBooks(bookID, bookTitle, bookAuthor); //addBooks method is then called and throws each part into their respective objects for each book (each row is a new book)
                }
            }
            System.out.println("All books from the designated file have been added: " + fileName); //confrimation of addition of books to the LMS
        }
        catch (FileNotFoundException e) {
            System.out.println("Designated file has not been found... " + fileName); // exception error is then thrown to let the user know that the file path cannot be found or the document is not formatted for the console to understand
        }
    }

    public void deleteBooksID(String bookID) { //deleteBooks method created for option 2 in the menu
        for (bookInfo book : books) {
            if (bookID.equalsIgnoreCase(bookID)) { //using the equalsignorecase method within the if statement, regardless of case the deleteBooksID method will recognize if the ID is attached to a present book or not
                books.remove(book); //if there is a book with a matching ID the book will be removed entirely hence the .remove(book) method
                System.out.println("Removal completed successfully: " + book); //confirmation text that the book has been removed
                return;
            }
        }
        System.out.println("Book with the intended removal of this ID not found: " + bookID); //if there is no matching ID console prints a statement that there is no ID matching along with the ID that was input
    }

    public void deleteBooksTitle (String bookTitle) { // deleteBooksTitle method created for option 3 in the menu
        for (bookInfo book : books) {
            if(book.bookTitle.equalsIgnoreCase(bookTitle)) { // using equalsIgnoreCase method within the if statement, regardless of case the deleteBooksTitle method will recognize if the Title is attached to a present book or not
                books.remove(book);
                System.out.println("Removal completed successfully: " + book); //confirmation text
                return;
            }
        }
        System.out.println("Book with the intended removal of this title not found: " + bookTitle); // if there is no matching title the console prints a statement that there is no title matching along with the title that was given
    }

    public void checkOutBooksTitle (String bookTitle) { // checkOutBooksTitle method created for option 4 in the menu
        for(bookInfo book : books) {
            if (book.bookTitle.equalsIgnoreCase(bookTitle)) { //equalsIgnoreCase method used same as the deleteBooksTitle method even though the book is not being deleted
                if (!book.checkedOut) {
                    book.checkedOut = true; //if statement is used so that if the book has not been already been checked out, the boolean for the particular book is set to true
                    System.out.println("Designated book has been checked out successfully: " + book); //confirmation text
                }
                else {
                    System.out.println("Designated book has already been checked out: " + book); // else statement is put for if the book has already been checked out
                }
            }
        }
        System.out.println("Book intended to be checked out cannot be found: " + bookTitle); // if there is no matching title from what the user has given this message will be thrown
    }
    public void checkInBooksTitle (String bookTitle) { //checkInBooksTitle method created for option 5 in the menu
        for (bookInfo book : books) {
            if(book.bookTitle.equalsIgnoreCase(bookTitle)) { // same equalsIgnoreCase method is used as the one above
                if (book.checkedOut) {
                    book.checkedOut = false; // if statement is used so that if the book has been checked out and not checked back in, the boolean for the book is set to false
                    System.out.println("Designated book has been checked in successfully: " + book); //confirmation text
                }
                else {
                    System.out.println("Designated book has not been checked out: " + book); // else statement is put so that if the book has not been checked out yet, it cannot be checked in
                }
            }
        }
        System.out.println("Book intended to be checked in cannot be found: " + bookTitle); // if there is no matching title from what the user has given this message will be thrown
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
