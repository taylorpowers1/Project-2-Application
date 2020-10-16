// --== CS400 File Header Information ==--
// Name: Taylor Powers
// Email: tmpowers@wisc.edu
// Team: EB
// Role: Front End Developer
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: none

import java.util.Scanner;

/**
 * This is the LibraryInteract class used to achieve human-computer interaction
 * while implementing the different functionalities of the RedBlackTree
 **/
public class LibraryInteract {

	/**
	 * This method prompts the user with different interactive choices, and
	 * reads the user's input to carry out the intended task of the input
	 **/
	public static void main(String args[]) {
		Library library = new Library();
		System.out.println("Welcome to the Madison Public Library.");
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"What do you want to do?\n1. (O) Check out a book from the library\n2. (I) Check in a book back into the library\n"
						+ "3. (C) Check the availabilty of book\n4. (G) Get information about a book\n5. (A) Add a new book to the library\n"
						+ "6. (V) View the contents of the library\n7. (E) Exit out of the library");
		String input = sc.nextLine().substring(0, 1).toUpperCase();

		while (true) {
			if (input.equals("V")) {
				printLibrary(library);
				input = sc.nextLine().substring(0, 1).toUpperCase();
			} else if (input.equals("A")) {
				System.out.println("What is the title of the book?");
				String title = sc.nextLine().strip().toLowerCase();
				title = title.substring(0, 1).toUpperCase()
						+ title.substring(1);
				System.out.println("Who is the author?");
				String author = sc.nextLine().strip().toLowerCase();
				int space = author.indexOf(" ");
				if (space != -1) {
					String first = author.substring(0, space);
					String last = author.substring(space + 1);
					String cap = Character.toString(first.charAt(0))
							.toUpperCase();
					first = cap + first.substring(1);
					cap = Character.toString(last.charAt(0)).toUpperCase();
					last = cap + last.substring(1);
					author = first + " " + last;
				} else {
					author = author.substring(0, 1).toUpperCase()
							+ author.substring(1);

				}
				System.out.println("What is the book's ISBN number?");
				int ISBN = 0;
				try {
					ISBN = sc.nextInt();
					insertBook(library, ISBN, title, author);
				} catch (Exception e) {
					System.out.println("\nInvalid ISBN number\n");
					System.out.println(
							"What do you want to do?\n1. (O) Check out a book from the library\n2. (I) Check in a book back into the library\n"
									+ "3. (C) Check the availabilty of book\n4. (G) Get information about a book\n5. (A) Add a new book to the library\n"
									+ "6. (V) View the contents of the library\n7. (E) Exit out of the library");
				}
				sc.nextLine();
				input = sc.nextLine().substring(0, 1).toUpperCase();
			} else if (input.equals("E")) {
				System.out.println("Thanks for using the Library. Goodbye!");
				break;
			} else if (input.equals("G")) {
				System.out.println("Enter an ISBN to get book info: ");
				int ISBN = 0;
				try {
					ISBN = sc.nextInt();
					getInfo(library, ISBN);
				} catch (Exception e) {
					System.out.println("\nInvalid ISBN number\n");
					System.out.println(
							"What do you want to do?\n1. (O) Check out a book from the library\n2. (I) Check in a book back into the library\n"
									+ "3. (C) Check the availabilty of book\n4. (G) Get information about a book\n5. (A) Add a new book to the library\n"
									+ "6. (V) View the contents of the library\n7. (E) Exit out of the library");
				}
				sc.nextLine();
				input = sc.nextLine().substring(0, 1).toUpperCase();
			} else if (input.equals("C")) {
				System.out.println(
						"Enter the ISBN of the book to check availability: ");
				int ISBN = 0;
				try {
					ISBN = sc.nextInt();
					getInfo(library, ISBN);
				} catch (Exception e) {
					System.out.println("\nInvalid ISBN number\n");
					System.out.println(
							"What do you want to do?\n1. (O) Check out a book from the library\n2. (I) Check in a book back into the library\n"
									+ "3. (C) Check the availabilty of book\n4. (G) Get information about a book\n5. (A) Add a new book to the library\n"
									+ "6. (V) View the contents of the library\n7. (E) Exit out of the library");
				}
				checkAvailability(library, ISBN);
				sc.nextLine();
				input = sc.nextLine().substring(0, 1).toUpperCase();
			} else if (input.equals("O")) {
				System.out.println(
						"Enter the ISBN number of the book you would like to check out: ");
				int ISBN = 0;
				try {
					ISBN = sc.nextInt();
					checkOut(library, ISBN);
				} catch (Exception e) {
					System.out.println("\nInvalid ISBN number\n");
					System.out.println(
							"What do you want to do?\n1. (O) Check out a book from the library\n2. (I) Check in a book back into the library\n"
									+ "3. (C) Check the availabilty of book\n4. (G) Get information about a book\n5. (A) Add a new book to the library\n"
									+ "6. (V) View the contents of the library\n7. (E) Exit out of the library");
				}
				sc.nextLine();
				input = sc.nextLine().substring(0, 1).toUpperCase();
			} else if (input.equals("I")) {
				System.out.println(
						"Enter the ISBN number of the book you would like to check in: ");
				int ISBN = 0;
				try {
					ISBN = sc.nextInt();
					checkIn(library, ISBN);
				} catch (Exception e) {
					System.out.println("\nInvalid ISBN number\n");
					System.out.println(
							"What do you want to do?\n1. (O) Check out a book from the library\n2. (I) Check in a book back into the library\n"
									+ "3. (C) Check the availabilty of book\n4. (G) Get information about a book\n5. (A) Add a new book to the library\n"
									+ "6. (V) View the contents of the library\n7. (E) Exit out of the library");
				}
				sc.nextLine();
				input = sc.nextLine().substring(0, 1).toUpperCase();
			} else {
				System.out.println("invalid command.");
				System.out.println(
						"What do you want to do?\n1. (O) Check out a book from the library\n2. (I) Check in a book back into the library\n"
								+ "3. (C) Check the availabilty of book\n4. (G) Get information about a book\n5. (A) Add a new book to the library\n"
								+ "6. (V) View the contents of the library\n7. (E) Exit out of the library.");
				input = sc.nextLine().substring(0, 1).toUpperCase();
			}

		}
	}

	/**
	 * This method checks a book out of the library
	 * 
	 * @param library The Library to check a book out of
	 * @param ISBN    The ISBN number of the book to be checked out
	 */
	private static void checkOut(Library library, int ISBN) {
		try {
			boolean checkedOut = library.checkOut(ISBN);
			if (checkedOut) {
				System.out.println("\nThe book \""
						+ library.getBook(ISBN).getTitle() + "\" has "
						+ "successfuly been checked out.\n");
			} else {
				System.out.println(
						"\nThis book is not available to check out.\n");
			}
		} catch (Exception e) {
			System.out.println("\nThis book is not available to check out.\n");
		} finally {
			System.out.println(
					"What do you want to do?\n1. (O) Check out a book from the library\n2. (I) Check in a book back into the library\n"
							+ "3. (C) Check the availabilty of book\n4. (G) Get information about a book\n5. (A) Add a new book to the library\n"
							+ "6. (V) View the contents of the library\n7. (E) Exit out of the library");
		}
	}

	/**
	 * This method checks a book back into the library
	 * 
	 * @param library The Library to check a book into
	 * @param ISBN    The ISBN number of the book to be checked in
	 */
	private static void checkIn(Library library, int ISBN) {
		try {
			boolean checkedIn = library.checkIn(ISBN);
			if (checkedIn) {
				System.out.println("\nThe book \""
						+ library.getBook(ISBN).getTitle() + "\" has "
						+ "successfuly been checked into the libary.\n");
			} else {
				System.out.println(
						"\nThe book can not be checked in because it is already in the library.\n");
			}
		} catch (Exception e) {
			System.out.println(
					"\nThe book can not be checked in because it is not"
							+ " a book from the library.\n");
		} finally {
			System.out.println(
					"What do you want to do?\n1. (O) Check out a book from the library\n2. (I) Check in a book back into the library\n"
							+ "3. (C) Check the availabilty of book\n4. (G) Get information about a book\n5. (A) Add a new book to the library\n"
							+ "6. (V) View the contents of the library\n7. (E) Exit out of the library");
		}
	}

	/**
	 * This method gets the book title and author for the intended ISBN number
	 * 
	 * @param library The Library to get book information from
	 * @param ISBN    The ISBN number the user wants to get information about
	 **/
	private static void getInfo(Library library, int ISBN) {
		try {
			Library.Book book = library.getBook(ISBN);
			System.out.println("\nTitle: " + book.getTitle());
			System.out.println("Author: " + book.getAuthor() + "\n");
		} catch (Exception e) {
			System.out
					.println("\nSorry, this book is not inside the Library\n");
		} finally {
			System.out.println(
					"What do you want to do?\n1. (O) Check out a book from the library\n2. (I) Check in a book back into the library\n"
							+ "3. (C) Check the availabilty of book\n4. (G) Get information about a book\n5. (A) Add a new book to the library\n"
							+ "6. (V) View the contents of the library\n7. (E) Exit out of the library");
		}
	}

	/**
	 * This method allows the librarian to add a new book into the library
	 * 
	 * @param library The Library to insert a book into
	 * @ISBN the ISBN number the user wants to insert into the library
	 **/
	private static void insertBook(Library library, int ISBN, String title,
			String author) {
		try {
			library.add(ISBN, title, author);
			System.out.println("\nThe book \"" + title
					+ "\" has successfully been added to the library.\n");
		} catch (Exception e) {
			System.out.println("\nSorry, the book " + title
					+ " is already inside the library.\n");
		} finally {
			System.out.println(
					"What do you want to do?\n1. (O) Check out a book from the library\n2. (I) Check in a book back into the library\n"
							+ "3. (C) Check the availabilty of book\n4. (G) Get information about a book\n5. (A) Add a new book to the library\n"
							+ "6. (V) View the contents of the library\n7. (E) Exit out of the library");

		}

	}

	/**
	 * This method prints the ISBN number, title, and author of all the books in
	 * the library
	 * 
	 * @parm library The Library to print view
	 **/
	private static void printLibrary(Library library) {
		System.out.println("\nHere are the contents of the Library: \n");
		System.out.println(library.toString() + "\n");
		System.out.println(
				"What do you want to do?\n1. (O) Check out a book from the library\n2. (I) Check in a book back into the library\n"
						+ "3. (C) Check the availabilty of book\n4. (G) Get information about a book\n5. (A) Add a new book to the library\n"
						+ "6. (V) View the contents of the library\n7. (E) Exit out of the library");
	}

	/**
	 * This method checks if a book is currently in the library
	 * 
	 * @param library The Library to check if a book is available in
	 * @param ISBN    the ISBN number the user searches for in the library
	 **/
	private static void checkAvailability(Library library, int ISBN) {
		try {
			boolean available = library.checkAvailability(ISBN);
			if (available) {
				System.out.println("\nThis book is available.\n");
			} else {
				System.out.println("\nSorry, this book is not available.\n");
			}
		} catch (Exception e) {
			System.out.println("\nSorry, this book is not in the library.\n");
		} finally {
			System.out.println(
					"What do you want to do?\n1. (O) Check out a book from the library\n2. (I) Check in a book back into the library\n"
							+ "3. (C) Check the availabilty of book\n4. (G) Get information about a book\n5. (A) Add a new book to the library\n"
							+ "6. (V) View the contents of the library\n7. (E) Exit out of the library");
		}
	}

}
