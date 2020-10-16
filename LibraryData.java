// --== CS400 File Header Information ==--
// Name: Zachary Austin
// Email: zaustin@wisc.edu
// Team: EB
// TA: Keren Chen
// Lecturer: Florian Heimerl
// Notes to Grader: Sources below
// Outside Source: https://www.abebooks.com/books/Textbooks/top-isbn.shtml

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This data file loads data from a csv file containing 25 books with their titles, authors, and ISBNs
 * @author Zachary Austin
 */
public class LibraryData {
  
  private String[] bookTitle=new String[30]; // Array storing book titles
  private String[] bookInfo=new String[30]; // Array storing book authors and ISBN's
  protected int size;
  
  /**
   * Fetch book title by index
   * @param index of the book title
   * @return corresponding book title
   */
  public String getBookTitle(int i) {
    return bookTitle[i];
  }
  
  /**
   * Fetch book information by index
   * @param index of the book info
   * @return corresponding book info
   */
  public String getBookInfo(int i) {
    return bookInfo[i];
  }
  
  /**
   * Loading/Formatting Data
   */
public LibraryData()  {
    try {
      BufferedReader books=new BufferedReader(new FileReader("books.csv")); // importing csv data
      String[] header=books.readLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); 
      for (int i=0;i<header.length;i++) {
        header[i]=header[i].replaceAll("\"", ""); 
      }
      String temp=books.readLine();
      int index=0;
      while (temp != null) {
        String[] title=temp.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        for (int i=0;i<title.length;i++) {
          title[i]=title[i].replaceAll("\"", "");
        }
        String info=header[1]+": "+title[1]+"\n"+
                    header[2]+": "+title[2]+"\n"; // storing bookInfo as Strings
        temp=books.readLine();
        bookTitle[index]=title[0]; 
        bookInfo[index]=info;
        size+=1;
        index+=1;
      }    
      books.close();
    } catch (FileNotFoundException e) { // catching exceptions
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
  /**
   * Testing to check that LibraryData works as expected
   */
  public static void main(String args[]) {
    LibraryData data=new LibraryData();
    System.out.println(data.getBookTitle(24));
    System.out.println(data.getBookInfo(24));
    System.out.println(data.size);

  }
  
}