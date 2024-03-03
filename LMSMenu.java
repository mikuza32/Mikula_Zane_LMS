// Zane Mikula CEN-3024C-24668
// LMSMenu class
// The program objective is to provide a helpful and organized arraylist of the LMS for whatever the user needs. >
// The problem being solved is to be able to manipulate books into and out of the LMS by: adding, deleting, checking in/out, and >
// listing what is already in the LMS
import java.util.Scanner;
public class LMSMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //scanner method is imported and used for user input called as sc
        bookList list = new bookList(); // bookList method is used from the bookList class pertaining to ArrayList manipulation. Called as "list"

        while(true) { // while loop runs for as long as user stays in the LMS, closes when user chooses option #7
            System.out.println("#### LMS ####");
            System.out.println("1. Add Books from File path");
            System.out.println("2. Remove Books by ID");    //System greets the user with a menu of choices to add, delete by title or ID, check out or check in a book, or display the LMS created
            System.out.println("3. Remove books by Title");
            System.out.println("4. Check out Book by Title");
            System.out.println("5. Check in Book by Title");
            System.out.println("6. List Books");
            System.out.println("7. Close Application");
            System.out.print("Enter 1-7 pertaining to your needs: "); // user enters 1-7 pertaining to needs, any other choice will not work and will be told why
            int choice = sc.nextInt(); // choice is set as an int variable, then using the sc.nextInt() method to state that the next user input will be an int (1-4)
            sc.nextLine(); //sc.nextLine() method is used if there is a correct input from the user based on choice (1-7), then executing the intended block of code after it

            switch (choice) { //switch method is used as if else if statement, if 1 is chosen then the case 1 block will be ran until brought back to the menu and so on.
                case 1:
                    System.out.print("Enter the desired file name of books (Enter the desired file path please): ");

                    String fileName = sc.nextLine();                  // if choice is 1 is entered, case 1 is ran and then book information is added to the array; creates the new books ID, title, and author from the file added from the user

                    list.booksFromFile(fileName);
                    break;
                case 2:
                    System.out.print("Enter the desired book ID number in order to remove it: ");

                    String deleteBooksID = sc.nextLine();

                    list.deleteBooksID(deleteBooksID); //bookList class is called using the deleteBooksID method which takes the book's ID number and deletes the entire book from the Array (database)
                    break;
                case 3:
                    System.out.print("Enter the desired book title in order to remove it: ");

                    String deleteBooksTitle = sc.nextLine();

                    list.deleteBooksTitle(deleteBooksTitle); //book list class is called, uses the deleteBooksTitle method which takes the book's title, and deletes the entire book
                    break;
                case 4:
                    System.out.print("Enter the desired book title you would like to check out: ");

                    String checkOutTitle = sc.nextLine();

                    list.checkOutBooksTitle(checkOutTitle); //booklist class is called, uses the checkOutBooksTitle method which uses the books title to be checked out, then the boolean created for the book is set to true
                    break;
                case 5:
                    System.out.print("Enter the desired book title you would like to check in: ");

                    String CheckInTitle = sc.nextLine();

                    list.checkInBooksTitle(CheckInTitle); //bookList class is called, uses the checkInBooksTitle method which uses the books title to be checked in, then the boolean is set back to false for the book.
                    break;
                case 6:
                    list.listBooks();  //bookList class is called using the listBooks() method which lists all current books added by the user (formatted by the toString method in the bookInfo class). All books are from the .txt file submitted from the user
                    break;
                case 7:
                    System.out.println("Thank you for using the LMS Database..."); // greets the user for using the LMS application and closes the program using System.exit(0)

                    System.exit(0);
                    break;
                default:
                    System.out.println("Please choose 1-4. Any other option will not work. "); // the default response is set if the user enters anything other than 1-4 when instructed through the prompt


            }
        }
    }
}
