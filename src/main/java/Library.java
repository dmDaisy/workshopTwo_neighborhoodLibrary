import java.util.*;

public class Library {
//    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Book[] books = new Book[20];

        for (int i = 0; i < books.length; i++) {
            books[i] = new Book(i, "ISBN" + 10010 + i, "\"Title " + i + "\"");
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println( "Welcome to Neighborhood Library Online Application! Choose your option: " +
                                "\n1. Show Available Books" +
                                "\n2. Show Checked Out Books" +
                                "\n3. Exit - closes out of the application" +
                                "\nYour option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    showAvailableBooks(books, scanner);
                    continue;
                case "2":
                    showCheckedOutBooks(books, scanner);
                    continue;
                case "3":
                    running = false;
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }

        scanner.close();
    }

    public static void showAvailableBooks(Book[] books, Scanner scanner) {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (!book.isCheckedOut()) {
                System.out.println("ID: " + book.getId() + " | " + book.getIsbn() + " | " + book.getTitle());
            }
        }

        System.out.print("\nEnter Book ID to check out or any other integer to go back: ");

        int choice = scanner.nextInt();
        // notice: consumed the leftover newline
        scanner.nextLine();

        if (choice >= 0 && choice < books.length && !books[choice].isCheckedOut()) {
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();
            books[choice].checkOut(name);
            System.out.println("You've checked out " + books[choice].getTitle());
        }
        // else: return to main menu
    }

    public static void showCheckedOutBooks(Book[] books, Scanner scanner) {
        System.out.println("Checked Out Book:");
        for (Book book : books) {
            if (book.isCheckedOut()) {
                System.out.println("ID: " + book.getId() + " | " + book.getIsbn() + " | " + book.getTitle() +
                        " | Checked Out To: " + book.getCheckedOutTo());
            }
        }

        System.out.println("Enter 'C' to check in a book or 'X' (or anything else) to go back to the home screen: ");
        String input = scanner.nextLine().toUpperCase();

        if (input.equals("C")) {
            System.out.print("Enter the ID of the book: ");
            int id = scanner.nextInt();
            // notice: to consume left over newline
            scanner.nextLine();

            if (id >= 0 && id < books.length && books[id].isCheckedOut()) {
                books[id].checkIn();
                System.out.println(books[id].getTitle() + " checked in successfully!");
            } else {
                System.out.println("Invalid ID or book not checked out.");
            }
        }
        // else: go back to home screen
    }
}
