// --== CS400 File Header Information ==--
// Name: <Haoxuan Lu>
// Email: <hlu224@wisc.edu email address>
// Team: <EB>
// Role: <Back End Developer>
// TA: <Keren Chen>
// Lecturer: <Florian Heimerl>
// Notes to Grader: <optional extra notes>

import java.util.LinkedList;

/**
 * This is a library class used to store or get out books
 * 
 * @author Haoxuan Lu
 *
 */
public class Library {

  /**
   * This is a book class used to express various characters of book object
   * 
   * @author Haoxuan
   *
   */
  public static class Book implements Comparable<Book> {

    private int ISBN;
    private String title;
    private String author;
    private boolean checked; // represents the book's availability status

    /**
     * The constructor of book variable used to create a book object
     * 
     * @param ISBN   an integer represent the ISBN number of the book
     * @param title  a string represent the title of the book
     * @param author a string represent the author name of the book
     */
    public Book(int ISBN, String title, String author) {
      this.ISBN = ISBN;
      this.title = title;
      this.author = author;
      this.checked = false; // initially available (not checked out)
    }

    /**
     * The default constructor used to create a null book object
     */
    public Book() {
      this(0, null, null);
    }

    /**
     * The getter used to get the ISBN number for the book
     * 
     * @return an integer represent the ISBN number of the book
     */
    public int getISBN() {
      return this.ISBN;
    }

    /**
     * The getter used to get the title of the book
     * 
     * @return a string represent the title of the book
     */
    public String getTitle() {
      return this.title;
    }

    /**
     * The getter used to get the author name of the book
     * 
     * @return a string represent the author name of the book
     */
    public String getAuthor() {
      return this.author;
    }

    /**
     * The setter used to set or change the ISBN of the book
     * 
     * @param ISBN an int variable represent the ISBN number of the book
     */
    public void setISBN(int ISBN) {
      this.ISBN = ISBN;
    }

    /**
     * The setter used to set or change the title of the book
     * 
     * @param title an string variable represent the title of the book
     */
    public void setTitle(String title) {
      this.title = title;
    }

    /**
     * The setter used to set or change the title of the book
     * 
     * @param author an string variable represent the author of the book
     */
    public void setAuthor(String author) {
      this.author = author;
    }

    /**
     * Two Book objects are compared according to their ISBN number. Comparing to a larger ISBN will
     * result in a positive number, comparing to a smaller ISBN will result in a negative number,
     * comparing to the same ISBN will result in 0.
     */
    @Override
    public int compareTo(Book book) {
      return this.ISBN - book.ISBN;
    }
  }

    private RedBlackTree<Book> bookShelf;

    public Library() {
      this.bookShelf = bookShelf = new RedBlackTree<>();
    }
      
    
    /**
     * Adding an individual Book object into the Library.
     * 
     * @param ISBN   - ISBN of the book;
     * @param title  - title of the book;
     * @param author - author of the book.
     * @return true if added successfully, false otherwise.
     */
    public boolean addBook(int ISBN, String title, String author) {
      try {
        Book newBook = new Book(ISBN, title, author); // the Book to be added
        bookShelf.insert(newBook);
        return true;
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
    }

    /**
     * This method used to check whether the book with certain ISBN is in the library
     * 
     * @param ISBN of the Book user is looking for.
     * @return true if the book with this ISBN in the library, false otherwise
     */
    public boolean containsBook(int ISBN) {
      return getBook(ISBN) != null;
    }

    /**
     * Return corresponding Book of the specified ISBN.
     * 
     * @param ISBN of the Book user is looking for.
     * @return the corresponding Book of the ISBN.
     */
    public Book getBook(int ISBN) {
      RedBlackTree.Node<Book> checkedBook = bookShelf.root;
      while (checkedBook.data.ISBN != ISBN) { // find the book with the specified ISBN from root
                                              // node
        if (checkedBook.data.ISBN < ISBN) {
          if (checkedBook.rightChild != null)
            checkedBook = checkedBook.rightChild;
          else { // if the ISBN is not valid
            checkedBook = null;
            break;
          }
        } else if (checkedBook.data.ISBN > ISBN) {
          if (checkedBook.leftChild != null)
            checkedBook = checkedBook.leftChild;
          else { // if the ISBN is not valid
            checkedBook = null;
            break;
          }
        }
      }
      if (checkedBook != null)
        return checkedBook.data;
      return null; // no Book with the ISBN exists
    }

    /**
     * Get the title of specific book with its ISBN number
     * 
     * @param ISBN of the Book user is looking for.
     * @return a string represents the title of the specific book
     */
    public String getBookTitle(int ISBN) {
      if (containsBook(ISBN))
        return getBook(ISBN).getTitle();
      System.out.println("Sorry, this book is not in the library");
      return null;
    }

    /**
     * Get the author of specific book with its ISBN number
     * 
     * @param ISBN of the Book user is looking for.
     * @return a string represents the author of the specific book
     */
    public String getBookAuthor(int ISBN) {
      if (containsBook(ISBN))
        return getBook(ISBN).getAuthor();
      System.out.println("Sorry, this book is not in the library");
      return null;
    }

    /**
     * Simulate checking-out process of a book with specified ISBN.
     * 
     * @param the ISBN of Book user wants to check out.
     * @return true if checked out successfully, false otherwise.
     */
    public boolean checkOut(int ISBN) {
      if (!containsBook(ISBN) || getBook(ISBN).checked) {
        System.out.println("Sorry this book is not in the library or have been checked out.");
        return false; // Book doesn't exist or is checked out already
      }

      getBook(ISBN).checked = true; // Book checked out, set status to checked-out
      return true;
    }

    /**
     * Simulate checking-in process of a book with specified ISBN.
     * 
     * @param the ISBN of Book user wants to check in.
     * @return true if checked in successfully, false otherwise.
     */
    public boolean checkIn(int ISBN) {
      if (!containsBook(ISBN)) {
        System.out.println("Sorry, this book is not belong to our library");
        return false;
      }

      getBook(ISBN).checked = false; // Book checked in, set status to not checked-out
      return true;
    }

    /**
     * Check the availability (status) of the Book with certain ISBN.
     * 
     * @param the ISBN of Book that is checked
     * @return true if the book is in the Library, false otherwise
     */
    public boolean isAvailable(int ISBN) {
      return containsBook(ISBN) && !(getBook(ISBN).checked);
    }

    /**
     * Print the Books in Library in a level order traversal way, each Book's information will be
     * printed as follows: "ISBN: <Book's ISBN> Title: <Book's title> Author: <Book's author>"
     * 
     * @return a String of all the Book information
     */
    public String toString() {
      String output = "";
      LinkedList<RedBlackTree.Node<Book>> q = new LinkedList<>();
      q.add(bookShelf.root);
      while (!q.isEmpty()) {
        RedBlackTree.Node<Book> next = q.removeFirst();
        if (next.leftChild != null)
          q.add(next.leftChild);
        if (next.rightChild != null)
          q.add(next.rightChild);
        output = output + "ISBN: " + next.data.ISBN + "\nTitle: " + next.data.title + "\nAuthor: "
            + next.data.author;
        if (!q.isEmpty())
          output += "\n\n";
      }
      return output;
    }

}
