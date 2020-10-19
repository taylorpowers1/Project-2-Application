import java.util.LinkedList;

// --== CS400 File Header Information ==--
// Name: <Kairas Mistry>
// Email: <kbmistry@wisc.edu email address>
// Team: <EB>
// Role: <Back End Developer>
// TA: <Keren Chen>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>

/**
 * A library to store and return books. A user can enter in a book, or a user can get information
 * about a book.
 * 
 * @author Kairas
 *
 */
public class Library {

  /**
   * A book with an ISBN, title, and author, should be comparable so front-end can compare books
   * 
   * @author Kairas
   *
   */
  public static class Book implements Comparable<Book> {
    private int ISBN;
    private String title;
    private String author;
    private boolean checked;

    private Book() {
      this.ISBN = 0;
      this.title = null;
      this.author = null;
      this.checked = false;
    }

    /**
     * Constructor for book, with specific values
     * 
     * @param ISBN
     * @param title
     * @param author
     */
    public Book(int ISBN, String title, String author) {
      this.ISBN = ISBN;
      this.title = title;
      this.author = author;
      this.checked = false;
    }

    public int getISBN() {
      return ISBN;
    }

    public void setISBN(int iSBN) {
      ISBN = iSBN;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getAuthor() {
      return author;
    }

    public void setAuthor(String author) {
      this.author = author;
    }

    @Override
    public int compareTo(Book book) {
      return this.ISBN - book.ISBN;
    }

  }

  RedBlackTree<Book> library = new RedBlackTree<Book>();

  /**
   * Attempts to add a new book with the given parameters into the library
   * 
   * @param ISBN
   * @param title
   * @param author
   * @return True if the book was added successfully, False otherwise
   */
  public boolean addBook(int ISBN, String title, String author) {
    try {
      Book addBook = new Book(ISBN, title, author);
      library.insert(addBook);
      return true;
    } catch (Exception e) {
      System.out.println("The book could not be added.");
      // System.out.println(ISBN + ", " + title);
      return false;
    }
  }

  /**
   * Checks if the library contains a book with the requested ISBN
   * 
   * @param ISBN
   * @return True if the library contains the book, false otherwise
   */
  public boolean containsBook(int ISBN) {
    return getBook(ISBN) != null;
  }

  /**
   * Returns a Book object with the same ISBN as the given ISBN
   * 
   * @param ISBN
   * @return A Book object
   */
  public Book getBook(int ISBN) {
    RedBlackTree.Node<Book> currentNode = library.root;
    while (currentNode.data.getISBN() != ISBN) {
      if (ISBN > currentNode.data.getISBN()) {
        if (currentNode.rightChild != null)
          currentNode = currentNode.rightChild;
        else
          return null;
      } else if (ISBN < currentNode.data.getISBN()) {
        if (currentNode.leftChild != null)
          currentNode = currentNode.leftChild;
        else
          return null;
      } else {
        return null;
      }
    }
    return currentNode.data;
  }

  /**
   * Returns the title of the requested ISBN
   * 
   * @param ISBN
   * @return A String object
   */
  public String getBookTitle(int ISBN) {
    if (containsBook(ISBN))
      return getBook(ISBN).getTitle();
    System.out.println("The requested book title is not available");
    return null;
  }

  /**
   * Returns the author of the requested ISBN
   * 
   * @param ISBN
   * @return A String object
   */
  public String getBookAuthor(int ISBN) {
    if (containsBook(ISBN))
      return getBook(ISBN).getAuthor();;
    System.out.println("The requested book author is not available");
    return null;
  }

  /**
   * Checks out a book with the given ISBN.
   * 
   * @param ISBN
   * @return True if the book was successfully checked out, false otherwise.
   */
  public boolean checkOut(int ISBN) {
    if (!containsBook(ISBN)) {
      System.out.println("The requested ISBN is not in the library.");
      return false;
    }
    if (getBook(ISBN).checked) {
      System.out.println("The requested ISBN is already checked out.");
      return false;
    }
    getBook(ISBN).checked = true;
    return true;
  }

  /**
   * Checks in a book with the given ISBN.
   * 
   * @param ISBN
   * @return True if the book was successfully checked in, false otherwise.
   */
  public boolean checkIn(int ISBN) {
    if (!containsBook(ISBN)) {
      System.out.println("The library does not contain the requested book.");
      return false;
    }
    getBook(ISBN).checked = false;
    return true;
  }

  /**
   * Checks in the library if the book exists and is available
   * 
   * @param ISBN
   * @return True if the book is available, false otherwise
   */
  public boolean isAvailable(int ISBN) {
    if (!containsBook(ISBN) || getBook(ISBN).checked) {
      System.out.println("The requested book is not available.");
      return false;
    }
    return true;
  }

  public String toString() {
    RedBlackTree.Node<Book> currentNode = library.root;
    String total = toStringHelper(currentNode);
    return total.substring(0, total.length()-2);
  }

  private String toStringHelper(RedBlackTree.Node<Book> currentNode) {
    String total = "";
    if (currentNode != null) {
      total = total + "ISBN: " + currentNode.data.getISBN() + "\nTitle: "
          + currentNode.data.getTitle() + "\nAuthor: " + currentNode.data.getAuthor() + "\n\n";
    }
    if (currentNode.leftChild != null) {
      total = total + toStringHelper(currentNode.leftChild);
    }
    if (currentNode.rightChild != null) {
      total = total + toStringHelper(currentNode.rightChild);

    }
    return total;
  }
}
