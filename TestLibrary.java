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
  void addBook() {
    bookShelf.addBook(1234, "ABC", "CBA");
    bookShelf.addBook(3451, "CDA", "DBE");
    bookShelf.addBook(2090, "JOF", "IUGF"); 
  }
  
  /**
   * This method tests if the overridden compareTo method in nested Book class works as expected.
   */
  @Test
  void testBookCompareTo() {
    Library.Book a=new Library.Book(1,"A","a");
    Library.Book b=new Library.Book(2,"A","a");
    Library.Book c=new Library.Book(3,"A","a");
    assertEquals(a.compareTo(b)<0, true);
    assertEquals(b.compareTo(c)<0, true);
    assertEquals(c.compareTo(a)<0, false);
  }
  
  /**
   * This method tests if getBookTitle() and getBookAuthor() methods with a valid ISBN can return the 
   * corresponding Book with correct book Title and Author, and null if the ISBN is invalid. It also checks
   * the validity of getBook() method simultaneously (as it is implemented in other getInfo methods).
   */
  @Test
  void testGetBookInfo() {
    if (!bookShelf.getBookTitle(1234).equals("ABC") ||
        !bookShelf.getBookAuthor(1234).equals("CBA") ||
        !bookShelf.getBookTitle(3451).equals("CDA") ||
        !bookShelf.getBookAuthor(3451).equals("DBE") ||
        !bookShelf.getBookTitle(2090).equals("JOF") ||
        !bookShelf.getBookAuthor(2090).equals("IUGF"))
      fail("Method Library.getBook() doesn't work.");
    assertEquals(bookShelf.getBook(3100),null);
    assertEquals(bookShelf.getBookTitle(3101),null);
    assertEquals(bookShelf.getBookAuthor(3102),null);
  }
  
  /**
   * This method tests if added Books are marked available in the Library and are able to be checked out, and 
   * returns false when passed with invalid parameter values (such as duplicate values). 
   */
  @Test
  void testAddAvailable() {
    assertEquals(bookShelf.addBook(2561, "DFE", "DEF"), true);
    assertEquals(bookShelf.addBook(7589, "CDF", "ZXY"), true);
    assertEquals(bookShelf.addBook(2561, "DFE", "DEF"), false);
    assertEquals(bookShelf.isAvailable(1234),true);
    assertEquals(bookShelf.isAvailable(3451),true);
    assertEquals(bookShelf.isAvailable(2090),true);
    assertEquals(bookShelf.isAvailable(2561),true);
    assertEquals(bookShelf.isAvailable(7589),true);
    assertEquals(bookShelf.isAvailable(5820),false);
  }
  
  /**
   * This method tests if the added Books are printed in order and are inserted into RBTree as expected.
   */
  @Test
  void testInsertView() {
    bookShelf.addBook(7589, "CDF", "ZXY");
    bookShelf.addBook(8510, "DFE", "DEF");
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
  void testCheckOut() {
    assertEquals(bookShelf.checkOut(2090),true);
    assertEquals(bookShelf.isAvailable(2090),false);
    assertEquals(bookShelf.checkOut(3451),true);
    assertEquals(bookShelf.isAvailable(3451),false);
    assertEquals(bookShelf.checkOut(2090),false);
    assertEquals(bookShelf.checkOut(3109),false);
  }
  
  /**
   * This method tests if the containsBook() method recognizes the Books belong to the library (regardless of 
   * whether they are available or checked-out).
   */
  @Test
  void testContains() {
    assertEquals(bookShelf.containsBook(1234),true);
    assertEquals(bookShelf.containsBook(3451),true);
    bookShelf.checkOut(2090);
    assertEquals(bookShelf.containsBook(2090),true);
    bookShelf.checkOut(1234);
    assertEquals(bookShelf.containsBook(1234),true);
    bookShelf.checkOut(3451);
    assertEquals(bookShelf.containsBook(3451),true);
    assertEquals(bookShelf.containsBook(5429),false);
  }
  
  /**
   * This method tests if checkIn() can correctly change Availability status of corresponding Books with valid ISBN, and return
   * appropriate value (true/false) to indicate whether the book is successfully checked in.
   */
  @Test
  void testCheckIn() {
    bookShelf.checkOut(2090);
    bookShelf.checkOut(3451);
    assertEquals(bookShelf.checkIn(2090),true);
    assertEquals(bookShelf.isAvailable(2090),true);
    assertEquals(bookShelf.checkIn(3451),true);
    assertEquals(bookShelf.isAvailable(3451),true);
    assertEquals(bookShelf.checkIn(2090),true);
    assertEquals(bookShelf.checkOut(3109),false);
  }
  
  /**
   * This method tests the validity of input data.
   */
  @Test
  void testData() {
    LibraryData libInitial=new LibraryData();
    bookShelf=libInitial.bookShelf;
    if (!bookShelf.getBook(72876484).getTitle().equals("Public Finance") ||
        !bookShelf.getBook(72876484).getAuthor().equals("Harvey S Rosen") ||
        !bookShelf.getBook(70311366).getTitle().equals("Introduction to Manufacturing Processes") ||
        !bookShelf.getBook(70311366).getAuthor().equals("John Schey") ||
        !bookShelf.getBook(72398876).getTitle().equals("Managament of a Sales Force") ||
        !bookShelf.getBook(72398876).getAuthor().equals("Rosann Spiro") ||
        !bookShelf.getBook(72465239).getTitle().equals("Electric Machinery Fundamentals") ||
        !bookShelf.getBook(72465239).getAuthor().equals("Stephen Chapman") ||
        !bookShelf.getBook(72534958).getTitle().equals("Physical Chemistry") ||
        !bookShelf.getBook(72534958).getAuthor().equals("Ira N Levine"))
      fail("Initial data isn't implemented right.");
  }

}
