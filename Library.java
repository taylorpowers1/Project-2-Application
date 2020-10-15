// --== CS400 File Header Information ==--
// Name: Yuliang Peng
// Email: peng68@wisc.edu
// Team: EB
// Role: Test Engineer
// TA: Keren Chen
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.util.LinkedList;

/**
 * This class represents a Library to store Books using a RBTree.
 * @author Yuliang Peng
 */
public class Library {
    
  /**
   * This nested class represents an individual Book element that will be stored in Library.
   * @author Yuliang Peng
   */
  public static class Book implements Comparable<Book> {
    private int ISBN;
    private String title;
    private String author;
    private boolean checked; // represents the book's availability status
    
    public Book(int ISBN, String title, String author) throws NullPointerException{
      if (ISBN==0 || title==null || author==null) throw new NullPointerException();
      this.ISBN=ISBN;
      this.title=title;
      this.author=author;
      this.checked=false; // initially available (not checked out)
    }
    
    public int getISBN() {
      return ISBN;
    }
    
    public String getTitle() {
      return title;
    }
    
    public String getAuthor() {
      return author;
    }
    
    /**
     * Two Book objects are compared according to their ISBN number.
     * Comparing to a larger ISBN will result in a positive number, comparing to a smaller ISBN will result in a negative number,
     * comparing to the same ISBN will result in 0.
     */
    @Override
    public int compareTo(Book book) {
      return this.ISBN-book.ISBN;
    }
    
  }
  
  private RedBlackTree<Book> bookShelf=new RedBlackTree<>(); // the RBTree Books will be stored in.
  
  /**
   * Adding an individual Book object into the Library.
   * @param ISBN - ISBN of the book;
   * @param title - title of the book;
   * @param author - author of the book.
   * @return true if added successfully, false otherwise.
   */
  public boolean add(int ISBN, String title, String author) {
    try {
      Book newBook=new Book(ISBN,title,author); // the Book to be added
      bookShelf.insert(newBook);
    } catch (Exception e) { // if the book isn't added successfully (either because of a duplication or a null input)
      System.out.println(e.getMessage());
      return false;
    }
    return true;
  }
  
  /**
   * Return corresponding Book of the specified ISBN.
   * @param ISBN of the Book user is looking for.
   * @return the corresponding Book of the ISBN.
   */
  public Book getBook(int ISBN) {
    RedBlackTree.Node<Book> checkedBook=bookShelf.root;
    while (checkedBook.data.ISBN!=ISBN) { // find the book with the specified ISBN from root node
      if (checkedBook.data.ISBN<ISBN ) { 
        if (checkedBook.rightChild!=null)
          checkedBook=checkedBook.rightChild;
        else { // if the ISBN is not valid
          checkedBook=null;
          break;
        }
      }
      else if (checkedBook.data.ISBN>ISBN) {
        if (checkedBook.leftChild!=null)
          checkedBook=checkedBook.leftChild;
        else { // if the ISBN is not valid
          checkedBook=null;
          break;
        }
      }
    }
    if (checkedBook!=null) return checkedBook.data;
    return null; // no Book with the ISBN exists
  }
  
  /**
   * Simulate checking-out process of a book with specified ISBN.
   * @param the ISBN of Book user wants to check out.
   * @return true if checked out successfully, false otherwise.
   */
  public boolean checkOut(int ISBN) {
    if (getBook(ISBN)==null || getBook(ISBN).checked==true) return false; // Book doesn't exist or is checked out already
    getBook(ISBN).checked=true; // Book checked out, set status to checked-out
    return true;
  }
  
  /**
   * Simulate checking-in process of a book with specified ISBN.
   * @param the ISBN of Book user wants to check in.
   * @return true if checked in successfully, false otherwise.
   */
  public boolean checkIn(int ISBN) {
    if (getBook(ISBN)==null) return false; // Book doesn't belong to the Library
    getBook(ISBN).checked=false; // Book checked in, set status to not checked-out
    return true;
  }
  
  /**
   * Check the availability (status) of the Book with certain ISBN.
   * @param the ISBN of Book that is checked
   * @return true if the book is in the Library, false otherwise
   */
  public boolean checkAvailability(int ISBN) {
    if (getBook(ISBN)==null || getBook(ISBN).checked==true) return false; // Book not in the Library (either doesn't belong to it
                                                                          // or has already been checked out
    return true;
  }
  
  /**
   * Print the Books in Library in a level order traversal way, each Book's information will be printed as follows:
   * "ISBN: <Book's ISBN>
   *  Title: <Book's title>
   *  Author: <Book's author>"
   * @return a String of all the Book information
   */
  public String toString() {
    String output = "";
    LinkedList<RedBlackTree.Node<Book>> q = new LinkedList<>();
    q.add(bookShelf.root);
    while(!q.isEmpty()) {
        RedBlackTree.Node<Book> next = q.removeFirst();
        if(next.leftChild != null) q.add(next.leftChild);
        if(next.rightChild != null) q.add(next.rightChild);
        output =output+"ISBN: "+next.data.ISBN+"\nTitle: "+next.data.title+"\nAuthor: "+next.data.author;
        if(!q.isEmpty()) output += "\n\n";
    }
    return output;
  }
  
  /**
   * Simple visualization of the Library class.
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    Library bookShelf=new Library();
    bookShelf.add(286, "trwg", "aldb");
    bookShelf.add(765, "wifg", "slng");
    bookShelf.add(45421, "sufg", null);

    bookShelf.add(134,"ahefoh","sui");
    
    System.out.println(bookShelf.checkAvailability(134));
    System.out.println(bookShelf.toString());
  }

}
