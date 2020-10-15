// --== CS400 File Header Information ==--
// Name: Yuliang Peng
// Email: peng68@wisc.edu
// Team: EB
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
    bookShelf.add(1234, "ABC", "CBA");
    bookShelf.add(345, "CDA", "DBE");
    bookShelf.add(209, "JOF", "IUGF"); 
  }
  
  /**
   * This method tests the validity of input data.
   */
  @Test
  void testData() {
    
  }
  
  /**
   * This method tests if getBook() method with a valid ISBN can return the corresponding Book with correct book Title and Author,
   * and null if the ISBN is invalid.
   */
  @Test
  void testGetBook() {
    if (!bookShelf.getBook(1234).getTitle().equals("ABC") ||
        !bookShelf.getBook(1234).getAuthor().equals("CBA") ||
        !bookShelf.getBook(345).getTitle().equals("CDA") ||
        !bookShelf.getBook(345).getAuthor().equals("DBE") ||
        !bookShelf.getBook(209).getTitle().equals("JOF") ||
        !bookShelf.getBook(209).getAuthor().equals("IUGF"))
      fail("Method Library.getBook() doesn't work.");
    assertEquals(bookShelf.getBook(310),null);
  }
  
  /**
   * This method tests if added Books are in the Library and appear as Available (true).
   */
  @Test
  void testAddAvailable() {
    assertEquals(bookShelf.checkAvailability(1234),true);
    assertEquals(bookShelf.checkAvailability(345),true);
    assertEquals(bookShelf.checkAvailability(209),true);
    assertEquals(bookShelf.checkAvailability(582),false);
  }
  
  /**
   * This method tests if the added Books are printed in order.
   */
  @Test
  void testView() {
    assertEquals(bookShelf.toString(), "ISBN: 345\nTitle: CDA\nAuthor: DBE\n\n" + 
                                       "ISBN: 209\nTitle: JOF\nAuthor: IUGF\n\n" +
                                       "ISBN: 1234\nTitle: ABC\nAuthor: CBA");
  }
  
  /**
   * This method tests if checkOut() can correctly change Availability status of corresponding Books with valid ISBN, and return
   * appropriate value (true/false) to indicate whether the book is successfully checked out.
   */
  @Test
  void testCheckOut() {
    assertEquals(bookShelf.checkOut(209),true);
    assertEquals(bookShelf.checkAvailability(209),false);
    assertEquals(bookShelf.checkOut(345),true);
    assertEquals(bookShelf.checkAvailability(345),false);
    assertEquals(bookShelf.checkOut(209),false);
    assertEquals(bookShelf.checkOut(310),false);
  }
  
  /**
   * This method tests if checkIn() can correctly change Availability status of corresponding Books with valid ISBN, and return
   * appropriate value (true/false) to indicate whether the book is successfully checked in.
   */
  @Test
  void testCheckIn() {
    bookShelf.checkOut(209);
    bookShelf.checkOut(345);
    assertEquals(bookShelf.checkIn(209),true);
    assertEquals(bookShelf.checkAvailability(209),true);
    assertEquals(bookShelf.checkIn(345),true);
    assertEquals(bookShelf.checkAvailability(345),true);
    assertEquals(bookShelf.checkIn(209),true);
    assertEquals(bookShelf.checkOut(310),false);
  }

}
