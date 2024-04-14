// Zane Mikula CEN-3024C-24668
// LMSApp class
// This class is used for creating the logic behind the GUI processes and setting each individual button, text field, and text area of the applications GUI



import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.DriverManager;

public class LMSApp extends JFrame {
    private JPanel mainPanel;
    private JTextField insertFileNameText;
    private JButton insertFileNameButton;
    private JTextArea disTextArea;
    private JTextField bookIDText;
    private JButton deleteIDButton;
    private JTextField bookTitleText;                      //sets all of the JFrames distinctive TextFields, Buttons, and TextAreas for their respective methods
    private JButton deleteTitleButton;                     // I.E. the deleteBooksTitle method has: bookTitleText (JTextField), deleteTitleButton (JButton), and
                                                          // displays the input message or exception handling message to disTextArea
    private JTextField checkOutText;
    private JButton checkOutButton;
    private JTextField checkInText;
    private JButton checkInButton;
    private JTextArea listBooksArea;
    private JButton listBooksButton;
    private JButton closeApplication;
    private Connection connect; //connects the database to the GUI application

    public LMSApp() {
        setTitle("WELCOME TO THE LMS");   // the applications title in the GUI title bar
        setSize(500, 300); // sets the standard window size when the application is ran
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // setDefaultCloseOperation() method is used to exit the application when the window is closed, hence
        setContentPane(mainPanel); // setContentPane() method is used to hold of the objects created in the GUI, named mainPanel in the LMSApp.form
        setVisible(true); // setVisible() method is used to make the GUI window appear on the screen, hence the boolean
        connectDatabase();

        bookList bookList = new bookList(); //instantiates the bookList class and its respective methods

        insertFileNameButton.addActionListener(new ActionListener() { // addActionListener() method created to perform an action after the insertFileButton is selected
            @Override
            public void actionPerformed(ActionEvent e) { // actionPerformed() method is used to complete a specific set of actions for the user if they choose to enter a .txt file
                String fileName = insertFileNameText.getText(); // instantiates the fileName in the text box, hence the .getText() method
                try { // exception handling is used to produce a boolean type set of outputs based on the user entry
                    bookList.booksFromFile(fileName); // calls the bookList class, and uses the booksFromFile method to read, and add content from the .txt file to the LMS
                    disTextArea.setText(".txt file loaded successfully!"); // displays the success message in the disTextArea in the GUI
                    insertFileNameText.setText(""); // resets the text box to an empty text box for the user-friendly experience of multiple entries
                } catch (FileNotFoundException ex) { // catches that the user input could not be successfully entered to the LMS
                    disTextArea.setText("File not found."); // displays the unsuccessful message in the disTextArea in the GUI, letting the user know that the file could not be found
                }

            }
        });

        deleteIDButton.addActionListener(new ActionListener() { // addActionListener() method created to perform an action after the deleteIDButton is selected
            @Override
            public void actionPerformed(ActionEvent e) { // actionPerformed() method is used to complete a specific set of actions for the user if they choose to delete a book from the LMS using the unique ID
                String bookID = bookIDText.getText(); // instantiates the bookID in the text box, hence the .getText() method
                try{ // exception handling is used to produce a boolean type set of outputs based on the user entry
                    deleteBookIDData(bookID); // calls the bookList class, and uses the deleteBooksID method to delete a specific book from the LMS using its unique ID
                    disTextArea.setText("Book with the entered ID " + bookID + " deleted..."); // displays the success message in the disTextArea in the GUI
                    bookIDText.setText(""); // resets the text box to an empty text box for the user-friendly experience of multiple entries
                } catch (SQLException exception) { // catches that the user input could not be successfully completed because the ID does not exist
                    disTextArea.setText("ID not found... " + exception.getMessage()); // displays the unsuccessful message in the disTextArea in the GUI, letting the user know that the book could not be deleted
                }
            }
        });

        deleteTitleButton.addActionListener(new ActionListener() { // addActionListener() method created to perform an action after the deleteTitleButton is selected
            @Override
            public void actionPerformed(ActionEvent e) { // actionPerformed() method is used to complete a specific set of actions for the user if they choose to delete a book from the LMS using the unique title
                String bookTitle = bookTitleText.getText(); // instantiates the bookTitle in the text box, hence the .getText() method
                try { // exception handling is used to produce a boolean type set of outputs based on the user entry
                    deleteBookTitleData(bookTitle); // calls the bookList class, and uses the deleteBooksTitle method to delete a specific book from the LMS using its unique title
                    disTextArea.setText("Book with the entered Title " + bookTitle + " deleted..."); // displays the success message in the disTextArea in the GUI
                    bookTitleText.setText(""); // resets the text box to an empty text box for the user-friendly experience of multiple entries
                } catch (SQLException exception) { // catches that the user input could not be successfully completed because the title does not exist
                    disTextArea.setText("Title not found... " + exception.getMessage()); // displays the unsuccessful message in the disTextArea in the GUI, letting the user know that the book could not be deleted
                }
            }
        });

        checkOutButton.addActionListener(new ActionListener() { // addActionListener() method created to perform an action after the checkOutButton is selected
            @Override
            public void actionPerformed(ActionEvent e) { // actionPerformed() method is used to complete a specific set of actions for the user if they choose to check out a book from the LMS using the unique title
                String bookTitle = checkOutText.getText(); // instantiates the bookTitle in the text box, hence the .getText() method
                try { // exception handling is used to produce a boolean type set of outputs based on the user entry
                    checkOutBookData(bookTitle); // calls the bookList class, and uses the checkOutBooksTitle method to check out a specific book from the LMS using its unique title
                    disTextArea.setText("The book " + bookTitle + " has been checked out..."); // displays the success message in the disTextArea in the GUI
                    checkOutText.setText(""); // resets the text box to an empty text box for the user-friendly experience of multiple entries
                } catch (SQLException exception) { // catches that the user input could not be successfully completed because the title does not exist
                    disTextArea.setText("Error... " + exception.getMessage()); // displays the unsuccessful message in the disTextArea in the GUI, letting the user know that the book could not be checked out
                }
            }
        });

        checkInButton.addActionListener(new ActionListener() { // addActionListener() method created to perform an action after the checkInButton is selected
            @Override
            public void actionPerformed(ActionEvent e) { // actionPerformed() method is used to complete a specific set of actions for the user if they choose to check in a book from the LMS using the unique title
                String bookTitle = checkInText.getText(); // instantiates the bookTitle in the text box, hence the .getText() method
                try{ // exception handling is used to produce a boolean type set of outputs based on the user entry
                    checkInBookData(bookTitle); // calls the bookList class, and uses the checkInBooksTitle method to check in a specific book from the LMS using its unique title
                    disTextArea.setText("The book " + bookTitle + " has been checked in..."); // displays the success message in the disTextArea in the GUI
                    checkInText.setText(""); // resets the text box to an empty text box for the user-friendly experience of multiple entries
                } catch (SQLException exception) { // catches that the user input could not be successfully completed because the title does not exist
                    disTextArea.setText("Error... " + exception.getMessage()); // displays the unsuccessful message in the disTextArea in the GUI, letting the user know that the book could not be checked in
                }
            }
        });

        listBooksButton.addActionListener(new ActionListener() { // addActionListener() method created to perform an action after the listBooksButton is selected
            @Override
            public void actionPerformed(ActionEvent e) { // actionPerformed() method is used to complete a specific set of actions for the user if they choose to display all the current books in the LMS
                try{ // exception handling is used to produce a boolean type set of outputs based on the user entry
                    String listBooksData = listBooksData(); // instantiates the bookListText in the text box, hence the .getText() method and calls the bookList class using the listBooks() to display all books in the LMS
                    listBooksArea.setText(listBooksData);  // displays all the books currently in the LMS in a separate text area using the listBooksArea, JTextArea
                    disTextArea.setText("List has been displayed below! "); // displays the success message in the disTextArea in the GUI
                } catch (SQLException exception) { // catches that the user input could not be successfully completed because there are no books currently in the LMS
                    listBooksArea.setText("Error... " + exception.getMessage()); // displays the unsuccessful message in the listBooksArea in the GUI, letting the user know that there was an error displaying the LMS
                }
            }
        });

        closeApplication.addActionListener(new ActionListener() { // addActionListener() method created to perform an action after the closeApplicationButton is selected
            @Override
            public void actionPerformed(ActionEvent e) { // actionPerformed() method is used to complete a specific set of actions for the user if they choose to close the application
                System.exit(0); // exits out of the application and stops running
            }
        });
    }

    private void connectDatabase() {
        try { //trys to connect to the given database called bookdata, by using the lastest JDBC driver, local server, username, and password to login
            Class.forName("com.mysql.cj.jdbc.Driver"); //sets the class name and reaches the mysql jdbc driver 8.3
            String url = "jdbc:mysql://localhost:3306/bookdata"; //the url needed to access the database being use in the application
            String username = "root"; //username login
            String password = "Whalers13!"; //password login
            connect = DriverManager.getConnection(url, username, password); //connects the JDBC driver for the application to use it (bookdata schema)
        } catch (ClassNotFoundException | SQLException sqlException) { //exception is caught if the database cannot be connected; database connectivity issues, jar file error etc.
            sqlException.printStackTrace(); //used to help handle the error
            JOptionPane.showMessageDialog(this, "Cannot connect to the intended database..."); // displays the error message as the database could not be loaded, or accessed
            System.exit(1); //closes the application as a whole since the database could not be accessed
        }
    }
    private void deleteBookTitleData(String bookTitle) throws SQLException { //deleteBookTitleData method is created to delete a book using its title from the bookdata database
        PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM booksinformation WHERE Title = ?"); //executes the intended query if option is selected, hence "prepareStatment" with SQL syntax
        preparedStatement.setString(1, bookTitle); //sets the query as a string as the data we are manipulating is a string
        preparedStatement.executeUpdate(); //fulfills the request and runs the query to produce the result the user is looking for
    }

    private void deleteBookIDData (String bookID) throws SQLException { //deleteBookIDData method is created to delete a book using its unique ID from the bookdata database
        PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM booksinformation WHERE ID = ?"); //executes the intended query if option is selected, hence "prepareStatment" with SQL syntax
        preparedStatement.setString(1, bookID); //sets the query as a string as the data we are manipulating is a string
        preparedStatement.executeUpdate(); //fulfills the request and runs the query to produce the result the user is looking for
    }

    private void checkOutBookData (String bookTitle) throws SQLException { //checkOutBookData method is created to manipulate the database by checking out a book by its respective title
        PreparedStatement preparedStatement = connect.prepareStatement("UPDATE booksinformation SET status = 'Checked out' WHERE Title = ?"); //executes the intended query if option is selected, hence "prepareStatment" with SQL syntax
        preparedStatement.setString(1, bookTitle); //sets the query as a string as the data we are manipulating is a string
        preparedStatement.executeUpdate(); //fulfills the request and runs the query to produce the result the user is looking for
    }

    private void checkInBookData (String bookTitle) throws SQLException { //checkInBookData method is created to manipulate the database by checking in a book by its respective title
        PreparedStatement preparedStatement = connect.prepareStatement("UPDATE booksinformation SET status = 'Checked in' WHERE Title = ?"); //executes the intended query if option is selected, hence "prepareStatment" with SQL syntax
        preparedStatement.setString(1, bookTitle); //sets the query as a string as the data we are manipulating is a string
        preparedStatement.executeUpdate(); //fulfills the request and runs the query to produce the result the user is looking for
    }

    private String listBooksData() throws SQLException { //listBooksData method is created to manipulate the database by listing all the contents of each book to the text area
        StringBuilder output = new StringBuilder();
        Statement statement = connect.createStatement(); //connects the statement to the database to run the query to display contents
        ResultSet resultSet = statement.executeQuery("SELECT * FROM booksinformation"); //query to display the most recent
        while (resultSet.next()) { // while loop is used to run the same formatting for each column and their contents
            output.append(resultSet.getInt("ID")).append("  |  ");
            output.append(resultSet.getString("Title")).append("  |  ");
            output.append(resultSet.getString("Genre")).append("  |  ");
            output.append(resultSet.getString("Author")).append("  |  "); //organizes and formats the contents of the database to look presentable, and legible
            output.append(resultSet.getString("Status")).append("  |  ");
            output.append(resultSet.getDate("dueDate")).append("\n");
        }
        return output.toString(); // uses toString method to return the formatted data and end the while loop
    }

    public static void main(String[] args) { // main method runs the entire program
        LMSApp myApp = new LMSApp(); // calls the LMSApp class and runs all the methods associated
    }



}
