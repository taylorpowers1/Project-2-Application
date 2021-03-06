// --== CS400 File Header Information ==--
// Name: Yuliang Peng
// Email: peng68@wisc.edu
// Team: EB
// Role: Test Engineer
// TA: Keren Chen
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class implements JUnit tests to test the implementation of Library and Data import.
 * @author Yuliang Peng
 */
public class TestLibrary {
  
  private Library bookShelf=new Library();
      
  /**
   * This method initializes the Library with three Books stored for each test method.
   */
  @BeforeEach
  public void addBook() {
    bookShelf.addBook(1234, "ABC", "CBA");
    bookShelf.addBook(3451, "CDA", "DBE");
    bookShelf.addBook(2090, "JOF", "IUGF"); 
  }
  
  /**
   * This method tests if the overridden compareTo method in nested Book class works as expected.
   */
  @Test
  public void testBookCompareTo() {
    Library.Book a=new Library.Book(1,"A","a");
    Library.Book b=new Library.Book(2,"A","a");
    Library.Book c=new Library.Book(3,"A","a");
    assertEquals(a.compareTo(b)<0, true);
    assertEquals(b.compareTo(c)<0, true);
    assertEquals(c.compareTo(a)<0, false); // ISBN value of c is supposed to be larger than that of a
  }
  
  /**
   * This method tests if getBookTitle() and getBookAuthor() methods with a valid ISBN can return the 
   * corresponding Book with correct book Title and Author, and null if the ISBN is invalid. It also checks
   * the validity of getBook() method simultaneously (as it is implemented in other getInfo methods).
   */
  @Test
  public void testGetBookInfo() {
    if (!bookShelf.getBookTitle(1234).equals("ABC") ||
        !bookShelf.getBookAuthor(1234).equals("CBA") ||
        !bookShelf.getBookTitle(3451).equals("CDA") ||
        !bookShelf.getBookAuthor(3451).equals("DBE") ||
        !bookShelf.getBookTitle(2090).equals("JOF") ||
        !bookShelf.getBookAuthor(2090).equals("IUGF"))
      fail("Method Library.getBook() doesn't work.");
    // Testing Books the Library doesn't have
    assertEquals(bookShelf.getBook(3100),null);
    assertEquals(bookShelf.getBookTitle(3101),null);
    assertEquals(bookShelf.getBookAuthor(3102),null);
  }
  
  /**
   * This method tests if added Books are marked available in the Library and are able to be checked out, and 
   * if Library returns false when passed with invalid parameter values (such as duplicate values). 
   */
  @Test
  public void testAddAvailable() {
    assertEquals(bookShelf.addBook(2561, "DFE", "DEF"), true);
    assertEquals(bookShelf.addBook(7589, "CDF", "ZXY"), true);
    assertEquals(bookShelf.addBook(2561, "DFE", "DEF"), false); // Book already in the Library
    assertEquals(bookShelf.isAvailable(1234),true);
    assertEquals(bookShelf.isAvailable(3451),true);
    assertEquals(bookShelf.isAvailable(2090),true);
    assertEquals(bookShelf.isAvailable(2561),true);
    assertEquals(bookShelf.isAvailable(7589),true);
    assertEquals(bookShelf.isAvailable(5820),false); // Book library doesn't have
  }
  
  /**
   * This method tests if the added Books are printed in order and are inserted into RBTree as expected.
   */
  @Test
  public void testInsertView() {
    bookShelf.addBook(7589, "CDF", "ZXY");
    bookShelf.addBook(8510, "DFE", "DEF");
    // Book in order
    assertEquals(bookShelf.toString(), "ISBN: 2090\nTitle: JOF\nAuthor: IUGF\n\n" + 
                                       "ISBN: 1234\nTitle: ABC\nAuthor: CBA\n\n" +
                                       "ISBN: 7589\nTitle: CDF\nAuthor: ZXY\n\n" +
                                       "ISBN: 3451\nTitle: CDA\nAuthor: DBE\n\n" +
                                       "ISBN: 8510\nTitle: DFE\nAuthor: DEF");
  }
  
  /**
   * This method tests if checkOut() can correctly change Availability status of corresponding Books 
   * with valid ISBN, and return appropriate value (true/false) to indicate whether the book is successfully 
   * checked out. It also further checks isAvailable() method simultaneously (by checking the availability 
   * of Books after they are checked out).
   */
  @Test
  public void testCheckOut() {
    assertEquals(bookShelf.checkOut(2090),true);
    assertEquals(bookShelf.isAvailable(2090),false);
    assertEquals(bookShelf.checkOut(3451),true);
    assertEquals(bookShelf.isAvailable(3451),false);
    // Whether unavailable or non-exist Books are able to be checked out
    assertEquals(bookShelf.checkOut(2090),false);
    assertEquals(bookShelf.checkOut(3109),false);
  }
  
  /**
   * This method tests if the containsBook() method recognizes the Books belong to the library (regardless of 
   * whether they are available or checked-out).
   */
  @Test
  public void testContains() {
    assertEquals(bookShelf.containsBook(1234),true);
    assertEquals(bookShelf.containsBook(3451),true);
    bookShelf.checkOut(2090);
    assertEquals(bookShelf.containsBook(2090),true); // Book still belongs to the Library
    bookShelf.checkOut(1234);
    assertEquals(bookShelf.containsBook(1234),true);
    bookShelf.checkOut(3451);
    assertEquals(bookShelf.containsBook(3451),true);
    assertEquals(bookShelf.containsBook(5429),false); // Book doesn't belong to the Library
  }
  
  /**
   * This method tests if checkIn() can correctly change Availability status of corresponding Books with valid ISBN, and return
   * appropriate value (true/false) to indicate whether the book is successfully checked in.
   */
  @Test
  public void testCheckIn() {
    bookShelf.checkOut(2090);
    bookShelf.checkOut(3451);
    assertEquals(bookShelf.checkIn(2090),true);
    assertEquals(bookShelf.isAvailable(2090),true);
    assertEquals(bookShelf.checkIn(3451),true);
    assertEquals(bookShelf.isAvailable(3451),true);
    assertEquals(bookShelf.checkIn(2090),true);
    assertEquals(bookShelf.checkOut(3109),false); // Book not in the Library
  }
  
  /**
   * This method tests the validity of input data and the implementation of initial data in LibraryInteract
   * class.
   */
  @Test
  public void testData() {
    LibraryData data=new LibraryData();
    Library books=new Library(); // Creating a new Library to store default Books
    for (int i = 0; i < data.size; i++) { // storing default Books into the Library
      String title = data.getBookTitle(i);
      String bookInfo = data.getBookInfo(i);
      String author = bookInfo.substring((bookInfo.indexOf(" ")) + 1,
          bookInfo.indexOf("\n"));
      String ISBN1 = bookInfo.substring((bookInfo.indexOf("ISBN")) + 6);
      int ISBN = Integer
          .parseInt(ISBN1.substring(0, ISBN1.indexOf("\n")));
      books.addBook(ISBN, title, author);
    }
    if (!books.getBook(72876484).getTitle().equals("Public Finance") ||
        !books.getBook(72876484).getAuthor().equals("Harvey S Rosen") ||
        !books.getBook(70311366).getTitle().equals("Introduction to Manufacturing Processes") ||
        !books.getBook(70311366).getAuthor().equals("John Schey") ||
        !books.getBook(72398876).getTitle().equals("Managament of a Sales Force") ||
        !books.getBook(72398876).getAuthor().equals("Rosann Spiro") ||
        !books.getBook(72465239).getTitle().equals("Electric Machinery Fundamentals") ||
        !books.getBook(72465239).getAuthor().equals("Stephen Chapman") ||
        !books.getBook(72534958).getTitle().equals("Physical Chemistry") ||
        !books.getBook(72534958).getAuthor().equals("Ira N Levine"))
      fail("Initial data isn't implemented right.");
  }

}
