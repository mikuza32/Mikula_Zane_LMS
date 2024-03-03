// Zane Mikula CEN-3024C-24668
// bookInfo class
// This class is used to create such objects that create the book itself in the arraylist; ID, Title, and Author and whether it is checked out or not. >
// toString method is used in order to format objects into a toString statement for when all data needs to be output on the console
public class bookInfo {
    String bookID; //bookID object is created as a string
    String bookTitle; //bookTitle object is created as a string
    String bookAuthor; //bookAuthor object is created as a string

    boolean checkedOut; //a boolean is set for whether the indicated book is checked out or not (true if it is, false if not)

    public bookInfo(String bookID, String bookTitle, String bookAuthor) {
        this.bookID = bookID;
        this.bookTitle = bookTitle; //bookInfo method sets the objects in order to be manipulated by the user, hence the this. statements and the bookInfo method
        this.bookAuthor = bookAuthor;
        this.checkedOut = false;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookID + ", Book Title: " + bookTitle + ", Author: " + bookAuthor + ", Checked out: " + checkedOut; //toString method is used to properly list each object of the book (ID, Title, Author, checkedOut or not) when stated to do so
    }
}
